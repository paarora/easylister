package com.cts.app.sms;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.verbs.TwiMLException;

public class SmsHelper {

	public static final String ACCOUNT_SID = "ACe21f9392d369e17169031b27b28b9549";
	  public static final String AUTH_TOKEN = "2a2af2b8def2905e77e7f685721f7d20";

	  public static void main(final String[] args) throws TwilioRestException, TwiMLException {

		  sendMessage("+14088595103", "This is a test message");
	    
	  }
	  
	  public static void sendMessage(String number, String message) throws TwilioRestException, TwiMLException{
		// Create a rest client
		    final TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

		    // Get the main account (The one we used to authenticate the client)
		    final Account mainAccount = client.getAccount();


		    // Send an sms
		    final SmsFactory smsFactory = mainAccount.getSmsFactory();
		    final Map<String, String> smsParams = new HashMap<String, String>();
		    smsParams.put("To", number); // Replace with a valid phone number
		    smsParams.put("From", "+14085122317"); // Replace with a valid phone number in your account
		    smsParams.put("Body", message);
		    smsFactory.create(smsParams);


		    // Make a raw HTTP request to the api... note, this is deprecated style
		    final TwilioRestResponse resp = client.request("/2010-04-01/Accounts", "GET", null);
		    if (!resp.isError()) {
		      System.out.println(resp.getResponseText());
		    }
		  
	  }
}
