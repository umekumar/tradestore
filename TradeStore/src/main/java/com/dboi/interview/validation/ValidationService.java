package com.dboi.interview.validation;

import com.dboi.interview.model.Trade;
import com.dboi.interview.store.TradeStore;

public interface ValidationService {
	boolean validateTrade(TradeStore store, Trade trade);
}
