package com.dboi.interview.trade.expire;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.dboi.interview.model.Trade;

import java.util.TimerTask;

public class TradeExpireTimer extends TimerTask {

	private Map<String, Trade> map;

	public TradeExpireTimer(Map<String, Trade> map) {
		this.map = map;
	}

	@Override
	public void run() {
		System.out.println("Trade Expiry Updater started ... ");
		for (Entry<String, Trade> entry : map.entrySet()){
			Date today = new Date();
			Trade trade = entry.getValue();
			Date maturityDate = trade.getMaturityDate();
			if(maturityDate.before(today)){
				trade.setExpired('Y');
			}
		}
		
	}

}
