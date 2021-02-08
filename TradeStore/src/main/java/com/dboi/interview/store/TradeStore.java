package com.dboi.interview.store;

import java.util.HashMap;
import java.util.Map;

import com.dboi.interview.model.Trade;

public class TradeStore{

	private static Map<String,Trade> store;
	
	public TradeStore(){
		store = new HashMap<String,Trade>();
	}

	public Map<String, Trade> getStore() {
		return store;
	}

	public void setStore(Map<String, Trade> store) {
		this.store = store;
	}
	
	

	
}
