package com.cts.app.db;

import java.util.Date;

public class TransactionRecord {
	
	long transactionId;
	String userid;
	double listingPrice;
	String sourceUrl;
	String targetUrl;
	Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getListingPrice() {
		return listingPrice;
	}
	public void setListingPrice(double listingPrice) {
		this.listingPrice = listingPrice;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	

}
