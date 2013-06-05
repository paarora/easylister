package com.cts.app.ebay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.AddItemCall;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.CountryCodeType;
import com.ebay.soap.eBLBaseComponents.CurrencyCodeType;
import com.ebay.soap.eBLBaseComponents.FeesType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.ListingDetailsType;
import com.ebay.soap.eBLBaseComponents.ListingDurationCodeType;
import com.ebay.soap.eBLBaseComponents.ListingSubtypeCodeType;
import com.ebay.soap.eBLBaseComponents.ListingTypeCodeType;
import com.ebay.soap.eBLBaseComponents.NameValueListArrayType;
import com.ebay.soap.eBLBaseComponents.NameValueListType;
import com.ebay.soap.eBLBaseComponents.PictureDetailsType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;

public class AddItemHelper {

	/**
	   * Build a sample item
	   * @return ItemType object
	   */
	  private static ItemType buildItem() throws IOException {

	      ItemType item = new ItemType();
	      // item title
	      item.setTitle("Title: ");
	      item.setSellerProvidedTitle("title");
	      
	      item.setVIN("wp0ab29911s685546");
	      
	      // item description
	      item.setDescription("Description: ");
	      
	      
	      // listing type
	      item.setListingType(ListingTypeCodeType.LEAD_GENERATION);
	      item.setListingSubtype2(ListingSubtypeCodeType.CLASSIFIED_AD);
	      
	      // Category
	      CategoryType cat = new CategoryType();
	      cat.setCategoryID("5332");
	      item.setPrimaryCategory(cat);
	      
	      item.setCategoryMappingAllowed(true);
	      item.setSite(SiteCodeType.E_BAY_MOTORS);
	      
	      ListingDetailsType ltype = new ListingDetailsType();
		  ltype.setLocalListingDistance("100");
		  item.setListingDetails(ltype);
	      
		  item.setCountry(CountryCodeType.US);
		  item.setCurrency(CurrencyCodeType.USD);
		  
		  item.setPostalCode("95126");
		  
	      AmountType amount = new AmountType();
	      amount.setValue(Double.valueOf("20.0"));
	      item.setStartPrice(amount);
	      
	      // listing duration
	      item.setListingDuration(ListingDurationCodeType.DAYS_7.value());
	      
	      // item location and country
	      item.setCountry(CountryCodeType.US);
	      
	      // item quality
	      item.setQuantity(new Integer(1));

	      // item condition, New
	      item.setConditionID(1000); 
	      
	      PictureDetailsType picDetailsType = new PictureDetailsType();
	   //   picDetailsType.setPictureURL(new String[] {"http://i.ebayimg.com/04/!BPoEM4!BWk~$(KGrHgoH-CgEjlLl1-OzBJ0kciTH9Q~~_1.JPG"});
	      picDetailsType.setExternalPictureURL(new String[] {"http://www.omenmachine.com/wp-content/uploads/2013/04/Acura-TSX-982.jpg"});

	      item.setPictureDetails(picDetailsType);
	      
	      List<NameValueListType> nvList = new ArrayList<NameValueListType>();
	      
	      NameValueListType nv = new NameValueListType();
	      nv.setName("Make");
	      nv.setValue(new String[]{"Acura"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Model");
	      nv.setValue(new String[]{"CL"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Year");
	      nv.setValue(new String[]{"20110000"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Mileage");
	      nv.setValue(new String[]{"2505"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Number of cylinders");
	      nv.setValue(new String[]{"6"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Model");
	      nv.setValue(new String[]{"CL"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Exterior color");
	      nv.setValue(new String[]{"Black"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Interior color");
	      nv.setValue(new String[]{"Black"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Transmission");
	      nv.setValue(new String[]{"Automatic"});
	      nvList.add(nv);
	      
	      nv = new NameValueListType();
	      nv.setName("Vehicle title");
	      nv.setValue(new String[]{"Clear"});
	      nvList.add(nv);
	      
	      
	      NameValueListArrayType nvArrayList = new NameValueListArrayType();
	      nvArrayList.setNameValueList(nvList.toArray(new NameValueListType[nvList.size()]));
	     
	      item.setItemSpecifics(nvArrayList);
	      
	      return item;
	  }

	  
	  
	  
	  public static void main(String[] args) {

		    try {

		      System.out.print("\n");
		      System.out.print("+++++++++++++++++++++++++++++++++++++++\n");
		      System.out.print("+ Welcome to eBay SDK for Java Sample +\n");
		      System.out.print("+  - ConsoleAddItem                   +\n");
		      System.out.print("+++++++++++++++++++++++++++++++++++++++\n");
		      System.out.print("\n");

		      // [Step 1] Initialize eBay ApiContext object
			  System.out.println("===== [1] Account Information ====");
		      ApiContext apiContext = getApiContext();
		      
		      // [Step 2] Create a new item object.
		      System.out.println("===== [2] Item Information ====");
		      ItemType item = buildItem();
		      
		      // [Step 3] Create call object and execute the call.
		      System.out.println("===== [3] Execute the API call ====");
		      System.out.println("Begin to call eBay API, please wait ...");
		      AddItemCall api = new AddItemCall(apiContext);
		      api.setItem(item);
		      FeesType fees = api.addItem();
		      System.out.println("End to call eBay API, show call result ...");
		      System.out.println();

		      // [Setp 4] Display results.
		      System.out.println("The list was listed successfully!");

		      double listingFee = eBayUtil.findFeeByName(fees.getFee(), "ListingFee").getFee().getValue();
		      System.out.println("Listing fee is: " + new Double(listingFee).toString());
		      System.out.println("Listed Item ID: " + item.getItemID());
		    }
		    catch(Exception e) {
		      System.out.println("Fail to list the item.");
		      e.printStackTrace();
		    }
		  }


	      
	/**
	   * Populate eBay SDK ApiContext object with data input from user
	   * @return ApiContext object
	   */
	  private static ApiContext getApiContext() throws IOException {  
	      ApiContext apiContext = new ApiContext();
	      ApiCredential cred = apiContext.getApiCredential();
	      cred.seteBayToken("AgAAAA**AQAAAA**aAAAAA**5/eHUQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCpOGqAWdj6x9nY+seQ**DRMBAA**AAMAAA**P2L1a61eaptdxxUeq1rVd0dTP4Mp/5tNEu43netiFj9X682mhnlkGTYExHUnfUIsGunbl9Zm9iME1vG63aBR90BU8f5/4VEY0H5+u0zkICR+6VXMaYjaP1eSlqLndx2WwBO4e1IAmawtJlowcPM4D8Hpj4pG6uFkBMHtjndBrvOGrQH+7rBaCewtNcCSHEavIr16CqCb40hJ62J5xwSSJc7qloAjnRngGkzc1LuHpfz0O4b0jiTvL8BTPVSjHrAyYYwI64t5ogYNB+FRYMRSI7iyeonGF3yEQqCTImYOzsw0a1RbqHAjTT+QsAW+150rJ1CQQaKEt89yDUBmSn2PW0m10A0OCBcT7heDpYjxt1Gq5/fleKDErUoLvskqE+yFMHDCxr/TpXtSDmS4fSZIgD9NKxeT6oN+VkTGaYULcNXgHAeDWjRsI8Ge0CosRqTp+9XBRa/T+FE8HaP9hkLQJDnNycMCJOUh1MMojqBHFGBhEBA/fZ8dIkwGqTL+TZstcVHko90ErBrd67LIUTLQwpT09SUHhXY9VIcXjiJ8+Bd/KTh8XTfqKm4vMZW5tqfSDINBFNebQQI3Z1xFMmihPXHu3uvPQzHOUjUVpAW7Ta/KhnxVu5WnH5vVhTL+usrNMG5eDzXigpopNRGijNfwTnMt2TNup4KI/sjCuIQThy/bVPCaDzLhLUWr1oeJO/7az/Ran50z/i7wkwIZabAAOKPKe6YMl9Pg9Bgo6hUgZDYKUgnKKo9nHNwwhU72ltQv");
	      apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");
	      apiContext.setSite(SiteCodeType.E_BAY_MOTORS);
	      return apiContext;
	  }


}
