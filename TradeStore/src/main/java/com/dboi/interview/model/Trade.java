package com.dboi.interview.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dboi.interview.validation.MaturityDateValidator;
import com.dboi.interview.validation.VersionValidator;

public class Trade {
	
	private String tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	private Date maturityDate;
	private Date createdDate;
	private char expired;
	private Enum versionStatus;
	private boolean maturityDateStatus;
	
	public Trade(){
		
	}
	public Trade(String tradeId, int version, String counterPartyId, String bookId, Date maturityDate, Date createdDate,
			char expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public char getExpired() {
		return expired;
	}

	public void setExpired(char expired) {
		this.expired = expired;
	}
	
	public Enum getVersionStatus() {
		return versionStatus;
	}
	
	public void setVersionStatus(Enum versionStatus) {
		this.versionStatus = versionStatus;
	}
	
	public boolean isMaturityDateStatus() {
		return maturityDateStatus;
	}
	
	public void setMaturityDateStatus(boolean maturityDateStatus) {
		this.maturityDateStatus = maturityDateStatus;
	}
	
	@Override
	public String toString() {
		return "Trade [Trade Id "+tradeId+", Version "+version+", Counter-Party Id "+counterPartyId+", Book-Id "+bookId+
				", Maturity Date "+maturityDate+", Created Date "+createdDate+", Expired "+expired+"]";
	}
	public List<Class<?>> getValidationList() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(VersionValidator.class);
		list.add(MaturityDateValidator.class);
		return list;
	}
}
