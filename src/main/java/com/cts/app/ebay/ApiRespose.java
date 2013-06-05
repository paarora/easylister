package com.cts.app.ebay;

import java.util.ArrayList;
import java.util.List;

import com.cts.app.parser.util.ErrorEnum;

public class ApiRespose {
	
	boolean isCallSuccessful = false;
	List<ErrorEnum> errors = new ArrayList<ErrorEnum>();
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
	public List<ErrorEnum> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorEnum> errors) {
		this.errors = errors;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	
	

}
