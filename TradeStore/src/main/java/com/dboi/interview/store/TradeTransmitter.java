package com.dboi.interview.store;

// Java Program to illustrate reading from FileReader 
// using BufferedReader 
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dboi.interview.model.Trade;

public class TradeTransmitter {

	public static List<Trade> doTradeTransmission() throws Exception {
		List<Trade> trades = new ArrayList<>();
		File file = new File("C:\\Users\\Umesh\\workspace\\TradeStore\\TRADE_TXN.DAT");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		br.readLine();
		
		while ((line = br.readLine()) != null) {
			Trade trade = new Trade();
			Date createdDate = null;
			String[] columns = line.split("\\|");
			String tradeId = columns[0];
			int version = Integer.parseInt(columns[1]);
			String counterPartyId = columns[2];
			String bookId = columns[3];
			Date maturityDate = new SimpleDateFormat("dd/MM/yyyy").parse(columns[4]);
			if ("".equals(columns[5])) {
				createdDate = new Date();
			} else {
				createdDate = new SimpleDateFormat("dd/MM/yyyy").parse(columns[5]);
			}
			char expired = columns[6].charAt(0);
			prepareTradeObj(trades, trade, createdDate, tradeId, version, counterPartyId, bookId, maturityDate, expired);
		}
		return trades;
	}

	private static void prepareTradeObj(List<Trade> trades, Trade trade, Date createdDate, String tradeId, int version,
			String counterPartyId, String bookId, Date maturityDate, char expired) {
		trade.setTradeId(tradeId);
		trade.setVersion(version);
		trade.setCounterPartyId(counterPartyId);
		trade.setBookId(bookId);
		trade.setMaturityDate(maturityDate);
		trade.setCreatedDate(createdDate);
		trade.setExpired(expired);
		trades.add(trade);
	}

}