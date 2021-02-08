package com.dboi.interview.launcher;

import java.util.Map;
import java.util.TimerTask;
import java.util.Map.Entry;

import com.dboi.interview.model.Trade;


public class TradeStorePrinter  extends TimerTask {

	private Map<String, Trade> map;

	public TradeStorePrinter(Map<String, Trade> map) {
		this.map = map;
	}
	
	@Override
	public void run() {
		System.out.println("Current Trades in Store >>>> ");
		for (Entry<String, Trade> entry : map.entrySet()){
			System.out.println(entry.getValue());
		}
	}
}
