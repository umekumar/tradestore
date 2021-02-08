package com.dboi.interview.validation;

import java.util.List;

import com.dboi.interview.model.Trade;
import com.dboi.interview.store.TradeStore;

public class TradeValidator{

	public boolean validateTrade(TradeStore store, Trade trade) {
		List<Class<?>> validators = trade.getValidationList();
		boolean status = false;
		for(Class validator : validators){
			try {
				ValidationService  service = (ValidationService) Class.forName(validator.getName()).newInstance();
				 service.validateTrade(store,trade);
				 status = true;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		return status;
	}

}
