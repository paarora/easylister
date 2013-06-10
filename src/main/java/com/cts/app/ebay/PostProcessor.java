package com.cts.app.ebay;

import com.cts.app.data.Vehicle;
import com.cts.app.db.DatabaseHelper;
import com.cts.app.db.TransactionRecord;
import com.cts.app.sms.SmsHelper;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.verbs.TwiMLException;

public class PostProcessor {

	String user_id = "testuser_paarora";
	String blacklistIds = "3N1CN7AP9CL906872,3N1BC1CP0BL502210,JN1AR5EF0AM231465";

	public void postProcess(Vehicle veh, ApiRespose resp) {
		int recordKey = insertRecordInDB(veh, resp);
		handleVINBlacklist(veh, recordKey);
	}

	private void handleVINBlacklist(Vehicle veh, int recordKey) {
		boolean isBlacklisted = false;
		String[] blacklistVins = blacklistIds.split(",");
		for (String vin : blacklistVins) {
			if (vin.equalsIgnoreCase(veh.getVin())) {
				isBlacklisted = true;
				break;
			}
		}
		if (isBlacklisted) {
			try {
				SmsHelper.sendMessage("+14088595103",
						"Blacklist VIN used, Transaction id : " + recordKey);
			} catch (TwilioRestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TwiMLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private int insertRecordInDB(Vehicle veh, ApiRespose resp) {
		TransactionRecord record = new TransactionRecord();
		record.setListingPrice(Float.parseFloat(veh.getPrice()));
		record.setSourceUrl(veh.getUrl());
		record.setTargetUrl(resp.getItemUrl());
		record.setUserid(user_id);
		return new DatabaseHelper().insertRecord(record);
	}

}
