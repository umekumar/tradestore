package com.dboi.interview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dboi.interview.exception.LowerVersionException;
import com.dboi.interview.model.Trade;
import com.dboi.interview.store.TradePersister;
import com.dboi.interview.store.TradeStore;
import com.dboi.interview.trade.expire.TradeExpireUpdater;
import com.dboi.interview.validation.TradeValidator;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class TradeStoreTest {

	private static TradeStore store;
	private static Trade trade1;
	private static Trade trade2;
	private static Trade trade3;
	private static Trade trade4;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@BeforeClass
	public static void setup() throws ParseException {
		store = new TradeStore();
		prepareObject1();
		prepareObject2();
		prepareObject3();
		prepareObject4();
	}

	private static void prepareObject4() throws ParseException {
		trade4 = new Trade();
		trade4.setTradeId("T3");
		trade4.setVersion(3);
		trade4.setCounterPartyId("CP-3");
		trade4.setBookId("B2");
		trade4.setMaturityDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2014"));
		trade4.setCreatedDate(new Date());
		trade4.setExpired('N');
	}

	private static void prepareObject3() throws ParseException {
		trade3 = new Trade();
		trade3.setTradeId("T2");
		trade3.setVersion(1);
		trade3.setCounterPartyId("CP-1");
		trade3.setBookId("B1");
		trade3.setMaturityDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2021"));
		trade3.setCreatedDate(new Date());
		trade3.setExpired('N');
	}

	private static void prepareObject2() throws ParseException {
		trade2 = new Trade();
		trade2.setTradeId("T2");
		trade2.setVersion(2);
		trade2.setCounterPartyId("CP-2");
		trade2.setBookId("B1");
		trade2.setMaturityDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2021"));
		trade2.setCreatedDate(new Date());
		trade2.setExpired('N');
	}

	private static void prepareObject1() throws ParseException {
		trade1 = new Trade();
		trade1.setTradeId("T1");
		trade1.setVersion(1);
		trade1.setCounterPartyId("CP-1");
		trade1.setBookId("B1");
		trade1.setMaturityDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2020"));
		trade1.setCreatedDate(new Date());
		trade1.setExpired('N');
	}

	@Test
	public void doValidateTest1() throws ParseException {
		TradeValidator tv = new TradeValidator();
		boolean status1 = tv.validateTrade(store, trade1);
		assertEquals(true, status1);

	}

	@Test
	public void doPersistTest1() throws ParseException, LowerVersionException {
		TradePersister persister = new TradePersister();
		boolean status = persister.doPersist(store, trade1);
		assertEquals(false, status);
	}

	@Test
	public void doValidateTest2() throws ParseException {
		TradeValidator tv = new TradeValidator();
		boolean status2 = tv.validateTrade(store, trade2);
		assertEquals(true, status2);
	}

	@Test
	public void doPersistTest2() throws ParseException, LowerVersionException {
		TradePersister persister = new TradePersister();
		boolean status = persister.doPersist(store, trade2);
		assertEquals(true, status);
	}

	@Test
	public void doValidateTest3() throws ParseException {
		TradeValidator tv = new TradeValidator();
		boolean status3 = tv.validateTrade(store, trade3);
		assertEquals(true, status3);

	}

	@Test
	public void doPersistTest3() throws Exception {
		TradePersister persister = new TradePersister();
		thrown.expect(LowerVersionException.class);
		thrown.expectMessage("Lower version Trade found, Trade Id " + trade3.getTradeId());
		persister.doPersist(store, trade3);
	}

	@Test
	public void doValidateTest4() throws ParseException {
		TradeValidator tv = new TradeValidator();
		boolean status4 = tv.validateTrade(store, trade4);
		assertEquals(true, status4);

	}

	@Test
	public void doPersistTest4() throws ParseException, LowerVersionException {
		TradePersister persister = new TradePersister();
		boolean status = persister.doPersist(store, trade4);
		assertEquals(false, status);
	}

	@Test
	public void doTradeExpiryUpdaterTest() throws ParseException {
		TradeExpireUpdater updater = new TradeExpireUpdater();
		boolean status = updater.tradeExpireUpdater(store.getStore());
		assertEquals(true, status);
	}
}
