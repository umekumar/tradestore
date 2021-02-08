package com.dboi.interview.store;

import com.dboi.interview.exception.LowerVersionException;
import com.dboi.interview.model.Trade;
import com.dboi.interview.tradeenum.VersionStatus;

public class TradePersister {

	public boolean doPersist(TradeStore store, Trade tr) throws LowerVersionException {
		if (!tr.isMaturityDateStatus()) {
			if (tr.getVersionStatus().equals(VersionStatus.LOWER)) {
				throw new LowerVersionException("Lower version Trade found, Trade Id "+tr.getTradeId());
			} else if (tr.getVersionStatus().equals(VersionStatus.EQUAL)
					|| tr.getVersionStatus().equals(VersionStatus.HIGHER)) {
				store.getStore().put(tr.getTradeId(), tr);
				return true;
			} 
		}
		return false;

	}

}
