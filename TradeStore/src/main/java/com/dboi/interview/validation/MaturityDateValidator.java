package com.dboi.interview.validation;

import java.util.Date;

import com.dboi.interview.model.Trade;
import com.dboi.interview.store.TradeStore;

public class MaturityDateValidator implements ValidationService{


	@Override
	public boolean validateTrade(TradeStore store, Trade trade) {
		Date maturityDateOfTrade = trade.getMaturityDate();
		Date today = new Date();
		boolean result = maturityDateOfTrade.before(today);
		trade.setMaturityDateStatus(result);
		return result;
	}

}
