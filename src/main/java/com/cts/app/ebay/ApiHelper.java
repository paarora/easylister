package com.cts.app.ebay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cts.app.data.Vehicle;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.AddItemCall;
import com.ebay.sdk.call.VerifyAddItemCall;
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

public class ApiHelper {
	
	private static ApiHelper instance;
	
	public static ApiHelper getInstance(){
		if(instance==null){
			instance = new ApiHelper();
		}
		return instance;
	}
	
	public ApiRespose verifyAddItem(Vehicle veh) {
		ApiRespose response = new ApiRespose();
		try {
			ApiContext apiContext = getApiContext();
			ItemType item = buildItem(veh);

			VerifyAddItemCall api = new VerifyAddItemCall(apiContext);
			api.setItem(item);
			FeesType fees = api.verifyAddItem();
			double listingFee = eBayUtil
					.findFeeByName(fees.getFee(), "ListingFee").getFee()
					.getValue();
			response.setFee(new Double(listingFee));
			response.setCallSuccessful(true);
		} catch (Exception e) {
			response.setCallSuccessful(false);
			System.out.println("Fail to list the item.");
			e.printStackTrace();
		}
		return response;
	}

	public ApiRespose addItem(Vehicle veh) {
		ApiRespose response = new ApiRespose();
		try {
			ApiContext apiContext = getApiContext();
			ItemType item = buildItem(veh);

			AddItemCall api = new AddItemCall(apiContext);
			api.setItem(item);
			FeesType fees = api.addItem();
			double listingFee = eBayUtil
					.findFeeByName(fees.getFee(), "ListingFee").getFee()
					.getValue();
			String itemUrl = "http://cgi.sandbox.ebay.com/ebaymotors/?cmd=ViewItem&item=" + item.getItemID();
			response.setItemUrl(itemUrl);
			response.setFee(new Double(listingFee));
			response.setCallSuccessful(true);
		} catch (Exception e) {
			response.setCallSuccessful(false);
			System.out.println("Fail to list the item.");
			e.printStackTrace();
		}
		return response;
	}

	private ApiContext getApiContext() throws IOException {
		ApiContext apiContext = new ApiContext();
		ApiCredential cred = apiContext.getApiCredential();
		cred.seteBayToken("AgAAAA**AQAAAA**aAAAAA**5/eHUQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCpOGqAWdj6x9nY+seQ**DRMBAA**AAMAAA**P2L1a61eaptdxxUeq1rVd0dTP4Mp/5tNEu43netiFj9X682mhnlkGTYExHUnfUIsGunbl9Zm9iME1vG63aBR90BU8f5/4VEY0H5+u0zkICR+6VXMaYjaP1eSlqLndx2WwBO4e1IAmawtJlowcPM4D8Hpj4pG6uFkBMHtjndBrvOGrQH+7rBaCewtNcCSHEavIr16CqCb40hJ62J5xwSSJc7qloAjnRngGkzc1LuHpfz0O4b0jiTvL8BTPVSjHrAyYYwI64t5ogYNB+FRYMRSI7iyeonGF3yEQqCTImYOzsw0a1RbqHAjTT+QsAW+150rJ1CQQaKEt89yDUBmSn2PW0m10A0OCBcT7heDpYjxt1Gq5/fleKDErUoLvskqE+yFMHDCxr/TpXtSDmS4fSZIgD9NKxeT6oN+VkTGaYULcNXgHAeDWjRsI8Ge0CosRqTp+9XBRa/T+FE8HaP9hkLQJDnNycMCJOUh1MMojqBHFGBhEBA/fZ8dIkwGqTL+TZstcVHko90ErBrd67LIUTLQwpT09SUHhXY9VIcXjiJ8+Bd/KTh8XTfqKm4vMZW5tqfSDINBFNebQQI3Z1xFMmihPXHu3uvPQzHOUjUVpAW7Ta/KhnxVu5WnH5vVhTL+usrNMG5eDzXigpopNRGijNfwTnMt2TNup4KI/sjCuIQThy/bVPCaDzLhLUWr1oeJO/7az/Ran50z/i7wkwIZabAAOKPKe6YMl9Pg9Bgo6hUgZDYKUgnKKo9nHNwwhU72ltQv");
		apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");
		apiContext.setSite(SiteCodeType.E_BAY_MOTORS);
		return apiContext;
	}

	private static ItemType buildItem(Vehicle veh) throws IOException {

		ItemType item = new ItemType();
		// item title
		item.setTitle(veh.getVehicleTitle());
		item.setSellerProvidedTitle(veh.getVehicleTitle());

		item.setVIN(veh.getVin());

		// item description
		item.setDescription(veh.getDescription());

		// listing type
		item.setListingType(ListingTypeCodeType.LEAD_GENERATION);
		item.setListingSubtype2(ListingSubtypeCodeType.CLASSIFIED_AD);

		String catID = null;
		for (MotorsCategoryEnum cEnum : MotorsCategoryEnum.values()) {
			if (cEnum.getMake().equalsIgnoreCase(veh.getMake())
					&& cEnum.getModel().equalsIgnoreCase(veh.getModel())) {
				catID = cEnum.getCategory();
				break;
			}
		}

		if (catID == null) {
			for (MotorsCategoryEnum cEnum : MotorsCategoryEnum.values()) {
				if (cEnum.getMake().equalsIgnoreCase(veh.getMake())
						&& cEnum.getModel().equalsIgnoreCase("Other")) {
					catID = cEnum.getCategory();
					break;
				}
			}
		}
		// Category
		CategoryType cat = new CategoryType();
		cat.setCategoryID(catID);
		item.setPrimaryCategory(cat);

		item.setCategoryMappingAllowed(true);
		item.setSite(SiteCodeType.E_BAY_MOTORS);

		ListingDetailsType ltype = new ListingDetailsType();
		ltype.setLocalListingDistance("100");
		item.setListingDetails(ltype);

		item.setCountry(CountryCodeType.US);
		item.setCurrency(CurrencyCodeType.USD);

		item.setPostalCode(veh.getZip());

		AmountType amount = new AmountType();
		amount.setValue(Double.valueOf(veh.getPrice()));
		item.setStartPrice(amount);

		// listing duration
		item.setListingDuration(ListingDurationCodeType.DAYS_7.value());

		// item location and country
		item.setCountry(CountryCodeType.US);

		// item quality
		item.setQuantity(new Integer(1));

		if ("new".equalsIgnoreCase(veh.getCondition())) {
			item.setConditionID(1000);
		} else {
			item.setConditionID(7000);
		}

		PictureDetailsType picDetailsType = new PictureDetailsType();
		// picDetailsType.setPictureURL(new String[]
		// {"http://i.ebayimg.com/04/!BPoEM4!BWk~$(KGrHgoH-CgEjlLl1-OzBJ0kciTH9Q~~_1.JPG"});
		picDetailsType.setExternalPictureURL(veh.getImages().toArray(
				new String[veh.getImages().size()]));
		
		// TODO
		//picDetailsType.setExternalPictureURL(new String[]
		//                                		{"http://images.autotrader.com/scaler/544/408/images/2013/4/8/338/965/30866700495.338965775.IM1.MAIN.565x421_A.562x421.jpg"});
		
		item.setPictureDetails(picDetailsType);

		List<NameValueListType> nvList = new ArrayList<NameValueListType>();

		NameValueListType nv = new NameValueListType();
		nv.setName("Make");
		nv.setValue(new String[] { veh.getMake() });
		nvList.add(nv);

		nv = new NameValueListType();
		nv.setName("Model");
		nv.setValue(new String[] { veh.getModel() });
		nvList.add(nv);

		nv = new NameValueListType();
		nv.setName("Year");
		nv.setValue(new String[] { veh.getYear() + "0000" });
		nvList.add(nv);

		nv = new NameValueListType();
		nv.setName("Mileage");
		nv.setValue(new String[] { veh.getMileage() });
		nvList.add(nv);

		nv = new NameValueListType();
		nv.setName("Number of cylinders");
		nv.setValue(new String[] { veh.getCylinder() });
		nvList.add(nv);

		nv = new NameValueListType();
		nv.setName("Exterior color");
		nv.setValue(new String[] { veh.getExteriorColour() });
		nvList.add(nv);

		nv = new NameValueListType();
		nv.setName("Interior color");
		nv.setValue(new String[] { veh.getInteriorColour() });
		nvList.add(nv);

		nv = new NameValueListType();
		nv.setName("Transmission");
		nv.setValue(new String[] { veh.getTransmission() });
		nvList.add(nv);

		nv = new NameValueListType();
		nv.setName("Vehicle title");
		nv.setValue(new String[] { veh.getTitleStatus() });
		nvList.add(nv);

		NameValueListArrayType nvArrayList = new NameValueListArrayType();
		nvArrayList.setNameValueList(nvList
				.toArray(new NameValueListType[nvList.size()]));

		item.setItemSpecifics(nvArrayList);

		return item;
	}

}
