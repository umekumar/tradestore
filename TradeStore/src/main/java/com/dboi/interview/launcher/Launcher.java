package com.dboi.interview.launcher;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.dboi.interview.exception.LowerVersionException;
import com.dboi.interview.model.Trade;
import com.dboi.interview.store.TradePersister;
import com.dboi.interview.store.TradeStore;
import com.dboi.interview.store.TradeTransmitter;
import com.dboi.interview.trade.expire.TradeExpireUpdater;
import com.dboi.interview.validation.TradeValidator;

public class Launcher {

	public static void main(String[] args)  {
		TradeStore store = new TradeStore();
		
		// Transmission Of Trades from File to Trade System
		List<Trade> trades = doTransmission();
		
		// Validating and Persist Trades
		doValidationAndPersist(store, trades);
		
		// Trade Expiry Updater
		startTradeExpiryScheduler(store);
		
		// Print Current Status of Trade Store
		tradeStorePrinter(store);
	}

	private static void tradeStorePrinter(TradeStore store) {
		TradeStorePrinter printer = new TradeStorePrinter(store.getStore());
        Timer timeToPring = new Timer();
        timeToPring.scheduleAtFixedRate(printer, 0, 5*1000);
	}

	private static List<Trade> doTransmission() {
		List<Trade> trades = new ArrayList<>();
		try {
			trades = TradeTransmitter.doTradeTransmission();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return trades;
	}

	private static void doValidationAndPersist(TradeStore store, List<Trade> trades) {
		TradeValidator validator = new TradeValidator();
		TradePersister persister = new TradePersister();
		for (Trade trade : trades){
			validator.validateTrade(store, trade);
			try {
				persister.doPersist(store, trade);
			} catch (LowerVersionException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void startTradeExpiryScheduler(TradeStore store) {
		TradeExpireUpdater updater = new TradeExpireUpdater();
		try {
			updater.tradeExpireUpdater(store.getStore());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	}
	

	
}
