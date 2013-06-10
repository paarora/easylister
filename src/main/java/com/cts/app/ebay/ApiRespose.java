package com.cts.app.ebay;

import com.cts.app.parser.util.Errors;

public class ApiRespose {
	
	boolean isCallSuccessful = false;
	Errors errors = new Errors();
	Double fee = null;
	String itemUrl = null;
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public boolean isCallSuccessful() {
		return isCallSuccessful;
	}
	public void setCallSuccessful(boolean isCallSuccessful) {
		this.isCallSuccessful = isCallSuccessful;
	}
	public Errors getErrors() {
		return errors;
	}
	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	
	

}
