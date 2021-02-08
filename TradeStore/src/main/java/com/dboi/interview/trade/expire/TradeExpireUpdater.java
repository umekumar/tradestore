package com.dboi.interview.trade.expire;

import java.text.ParseException;
import java.util.Map;
import java.util.Timer;

import com.dboi.interview.model.Trade;

public class TradeExpireUpdater{
 
    public boolean tradeExpireUpdater(Map<String, Trade> map) throws ParseException {
    	TradeExpireTimer timer = new TradeExpireTimer(map); 
        long delay = 10; //1000 * 60 * 60 * 24;
        Timer t = new Timer();
        t.scheduleAtFixedRate(timer, 0, 5*1000);
        return true;
    }
}