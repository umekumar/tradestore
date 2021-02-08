package com.dboi.interview.validation;

import com.dboi.interview.model.Trade;
import com.dboi.interview.store.TradeStore;
import com.dboi.interview.tradeenum.VersionStatus;

public class VersionValidator implements ValidationService {

	@Override
	public boolean validateTrade(TradeStore store, Trade trade) {
		boolean tradeExist = store.getStore().containsKey(trade.getTradeId());
		if (tradeExist == true) {
			Trade tradeFromStore = store.getStore().get(trade.getTradeId());
			int versionOfTradefromStore = tradeFromStore.getVersion();
			int versonOfCurrentTrade = trade.getVersion();
			if (versonOfCurrentTrade < versionOfTradefromStore)
				trade.setVersionStatus(VersionStatus.LOWER);
			else if (versonOfCurrentTrade == versionOfTradefromStore)
				trade.setVersionStatus(VersionStatus.EQUAL);
			else
				trade.setVersionStatus(VersionStatus.HIGHER);
		} else {
			trade.setVersionStatus(VersionStatus.HIGHER);
		}
		if (trade.getVersionStatus() != null)
			return true;
		else
			return false;
	}

}
