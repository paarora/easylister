package com.cts.app.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.cts.app.data.ExtractDataHtmlHelper;
import com.cts.app.data.Vehicle;
import com.cts.app.parser.util.ParserConstants;

public class CarsItemParser implements ItemParser{

	@Override
	public Vehicle parseItem(String url) throws Exception {
		String vehicleTitle = null;
		String title = null;
		String vin = null;
		String year = null;
		String cylinder = null;
		String mileage = null;
		String description = null;
		Vehicle vehicle = new Vehicle();
		Parser parser = null;
		try {
			parser = new Parser(url);
			NodeList nodeList = parser.parse(null);

			nodeList = extract(nodeList, new TagNameFilter(
					ParserConstants.HTML));
			nodeList = extract(nodeList, new TagNameFilter(
					ParserConstants.BODY));
			NodeList divs = extract(nodeList, new TagNameFilter(
					ParserConstants.DIV));
			NodeList titleContents = extract(divs, new HasAttributeFilter(
					ParserConstants.CLASS,
					ParserConstants.BASIC_INFO));
			NodeList titleContent = extract(titleContents, new TagNameFilter(
					ParserConstants.C_H1));
			if(titleContent!=null && titleContent.elementAt(0)!=null)
				vehicleTitle = getValue(titleContent);
			if (vehicleTitle != null)
				vehicle.setVehicleTitle(vehicleTitle);

			NodeList priceContent = extract(titleContent, new TagNameFilter(
					ParserConstants.SPAN));
			if(priceContent!=null && priceContent.elementAt(0)!=null)
				vehicle.setPrice(ExtractDataHtmlHelper.getNumericString(getValue(priceContent)));
			ExtractDataHtmlHelper.extractMakeModel(vehicle, vehicleTitle);
			year = extractYear(vehicleTitle);
			if (year != null)
				vehicle.setYear(year);

			NodeList mileageContents = extract(divs, new HasAttributeFilter(
					ParserConstants.ID, ParserConstants.C_MILEAGE));
			NodeList mileageContent = extract(mileageContents,
					new TagNameFilter(ParserConstants.SPAN));
			NodeList mileageNode = extract(mileageContent,
					new HasAttributeFilter(ParserConstants.CLASS,
							ParserConstants.DATA));
			mileage = ExtractDataHtmlHelper.getNumericString(getValue(mileageNode));
			if (mileage != null)
				vehicle.setMileage(mileage);

			NodeList vinContents = extract(divs, new HasAttributeFilter(
					ParserConstants.ID, ParserConstants.C_VIN));
			NodeList vinContent = extract(vinContents, new TagNameFilter(
					ParserConstants.SPAN));
			NodeList vinNode = extract(vinContent, new HasAttributeFilter(
					ParserConstants.CLASS, ParserConstants.DATA));
			vin = getValue(vinNode);
			if (vin != null)
				vehicle.setVin(vin);

			NodeList engineContents = extract(divs, new HasAttributeFilter(
					ParserConstants.ID, ParserConstants.C_ENGINE));
			NodeList engineContent = extract(engineContents, new TagNameFilter(
					ParserConstants.SPAN));
			NodeList engineNode = extract(engineContent,
					new HasAttributeFilter(ParserConstants.CLASS,
							ParserConstants.DATA));
			String engineString = getValue(engineNode);
			if(engineString!=null)
				cylinder = extractCylinder(engineString);
			if (cylinder != null)
				vehicle.setCylinder(cylinder);

			NodeList bodyStyles = extract(divs, new HasAttributeFilter(
					ParserConstants.ID,
					ParserConstants.C_BODY_STYLE));
			NodeList bodyStyle = extract(bodyStyles, new TagNameFilter(
					ParserConstants.SPAN));
			NodeList bodyNode = extract(bodyStyle, new HasAttributeFilter(
					ParserConstants.CLASS, ParserConstants.DATA));
			/*String bodyType = getValue(bodyNode);
			Collection<String> bodyTypes = vehicle.getAllBodyTypes().values();
			for (String body : bodyTypes) {
				if (body.equalsIgnoreCase(bodyType)) {
					vehicle.setBodyType(body);
					break;
				} else
					vehicle.setBodyType(ParserConstants.OTHER);
			}*/

			NodeList extColors = extract(divs, new HasAttributeFilter(
					ParserConstants.ID,
					ParserConstants.C_EXTERIOR_COLOR));
			NodeList extColor = extract(extColors, new TagNameFilter(
					ParserConstants.SPAN));
			NodeList extColorNode = extract(extColor, new HasAttributeFilter(
					ParserConstants.CLASS, ParserConstants.DATA));
			String extColorValue = getValue(extColorNode);
			Collection<String> extColorTypes = vehicle.getAllExteriorColors().values();
			for (String color : extColorTypes) {
				if (color.equalsIgnoreCase(extColorValue)) {
					vehicle.setExteriorColour(color);
					break;
				} else
					vehicle.setExteriorColour(ParserConstants.OTHER);
			}

			NodeList intColors = extract(divs, new HasAttributeFilter(
					ParserConstants.ID,
					ParserConstants.C_INTERIOR_COLOR));
			NodeList intColor = extract(intColors, new TagNameFilter(
					ParserConstants.SPAN));
			NodeList intColorNode = extract(intColor, new HasAttributeFilter(
					ParserConstants.CLASS, ParserConstants.DATA));
			String intColorValue = getValue(intColorNode);
			Collection<String> intColorTypes = vehicle.getAllInteriorColors().values();
			for (String color : intColorTypes) {
				if (color.equalsIgnoreCase(intColorValue)) {
					vehicle.setInteriorColour(color);
					break;
				} else
					vehicle.setInteriorColour(ParserConstants.OTHER);
			}

			NodeList transmissionContents = extract(divs,
					new HasAttributeFilter(ParserConstants.ID,
							ParserConstants.C_TRANSMISSION));
			NodeList transmissionContent = extract(transmissionContents,
					new TagNameFilter(ParserConstants.SPAN));
			NodeList transmissionNode = extract(transmissionContent,
					new HasAttributeFilter(ParserConstants.CLASS,
							ParserConstants.DATA));
			String transmissionString = "";
			if (transmissionNode != null && transmissionNode.size() != 0) {
				transmissionString = getValue(transmissionNode);
				if (transmissionString.indexOf("auto") != -1
						|| transmissionString.indexOf("Auto") != -1
						|| transmissionString.indexOf("AUTO") != -1)
					vehicle.setTransmission(ParserConstants.AUTOMATIC);
				else if (transmissionString.indexOf("manua") != -1
						|| transmissionString.indexOf("Manua") != -1
						|| transmissionString.indexOf("MANUA") != -1)
					vehicle.setTransmission(ParserConstants.MANUAL);
			}
			
			NodeList sellerAddress = extract(divs,
					new HasAttributeFilter(ParserConstants.ID,
							ParserConstants.C_SELLER_ADDRESS));
			NodeList sellerAddressContent = extract(sellerAddress,
					new TagNameFilter(ParserConstants.SPAN));
			NodeList sellerAddressNode = extract(sellerAddressContent,
					new HasAttributeFilter(ParserConstants.CLASS,
							ParserConstants.DATA));
			String address = getSecondValue(sellerAddressNode);
			vehicle.setZip(ExtractDataHtmlHelper.extractZip(address));
			
			NodeList sellersNotes = extract(divs,
					new HasAttributeFilter(ParserConstants.ID,
							ParserConstants.SELLERNOTES));
			/*NodeList aboutVehicleContent = extract(aboutVehicleContets,
					new TagNameFilter(ParserConstants.DIV));
			NodeList aboutVehicle = extract(aboutVehicleContent,
					new HasAttributeFilter(ParserConstants.CLASS,
							ParserConstants.PADDED_BOX));
			String vehicleDescriptionString = sellersNotes.asString();
			description = vehicleDescriptionString.replaceAll(
					ParserConstants.SELLERS_NOTES, "");*/
			description = sellersNotes.asString();
			description = ExtractDataHtmlHelper.formatText(description);
			if (description != null)
				vehicle.setDescription(description);
			title = extractTitle(vehicleTitle, description);
			if (title != null)
				vehicle.setTitleStatus(title);

			NodeList thumbnailsContents = extract(divs, new HasAttributeFilter(
					ParserConstants.CLASS,
					ParserConstants.THUMBNAIL));
			if(thumbnailsContents!=null && thumbnailsContents.elementAt(0)!=null){
			NodeList thumbnails = extract(thumbnailsContents.elementAt(0)
					.getChildren(), new TagNameFilter(
					ParserConstants.SCRIPT));
			SimpleNodeIterator iterator = thumbnails.elements();
			List<String> images = new ArrayList<String>();
			while (iterator.hasMoreNodes()) {
				Node node = iterator.nextNode();
				String imageString = node.getFirstChild().toPlainTextString();
				String[] imageStrings = imageString.split("\'");
				String imageUrl = imageStrings[1].replaceAll(
						ParserConstants.MAIN,
						ParserConstants.SUPERSIZED);
				images.add(imageUrl);
			}
			vehicle.setImages(images);
			}
		} catch (Exception e) {
			throw e;
		}
		return vehicle;
	}

	private static NodeList extract(NodeList nodeList, NodeFilter filter) {
		return nodeList.extractAllNodesThatMatch(filter, true);
	}

	private String getValue(NodeList nodeValue) {
		String value = null;
		if (nodeValue != null && nodeValue.elementAt(0) != null) {
			value = nodeValue.elementAt(0).toPlainTextString();
			value = ExtractDataHtmlHelper.formatText(value);
			value = value.trim();
		}
		return value;
	}
	
	private String getSecondValue(NodeList nodeValue) {
		String value = null;
		if (nodeValue != null && nodeValue.elementAt(1) != null) {
			value = nodeValue.elementAt(1).toPlainTextString();
			value = ExtractDataHtmlHelper.formatText(value);
			value = value.trim();
		}
		return value;
	}

	private String extractTitle(String header, String plainTextDesc) {
		String cartitle = extractTitle(header, plainTextDesc,
				ParserConstants.CLEAR);
		if ("".equals(cartitle)) {
			cartitle = extractTitle(header, plainTextDesc,
					ParserConstants.SALVAGE);
		}
		return cartitle;
	}

	private String extractTitle(String header, String plainTextDesc,
			String regex) {
		if (header.toLowerCase().indexOf(regex) > 0
				|| plainTextDesc.toLowerCase().indexOf(regex) > 0) {
			return regex;
		}
		return "";
	}

	private String extractYear(String content) {
		Pattern p = Pattern.compile("(19|20)\\d\\d");
		Matcher m = p.matcher(content);
		String year = "";
		if (m.find()) {
			MatchResult re = m.toMatchResult();
			year = content.subSequence(re.start(), re.end()).toString();
		}
		return year;
	}

	private String extractCylinder(String plainTextDesc) {
		plainTextDesc = plainTextDesc.toLowerCase();
	//	plainTextDesc = plainTextDesc.replaceAll("\\W", "");
		String cylinder = "";
		if (plainTextDesc.indexOf("v4") > 0 || plainTextDesc.indexOf("V4") > 0
				|| plainTextDesc.indexOf("4-cyl") > 0
				|| plainTextDesc.indexOf("4-cylinder") > 0 
				|| plainTextDesc.indexOf("i4") > 0) {
			cylinder = "4";
		} else if (plainTextDesc.indexOf("v6") > 0
				|| plainTextDesc.indexOf("V6") > 0
				|| plainTextDesc.indexOf("6-cyl") > 0
				|| plainTextDesc.indexOf("6-cylinder") > 0) {
			cylinder = "6";
		}
		return cylinder;
	}

	public static void main(String args[]) throws Exception {
		CarsItemParser ca = new CarsItemParser();
		Vehicle vh = ca.parseItem("http://www.cars.com/go/search/detail.jsp?tracktype=newcc&csDlId=&csDgId=&listingId=106673284&listingRecNum=4&criteria=sf1Dir%3DDESC%26mkId%3D20077%26mdId%3D22274%26rd%3D30%26crSrtFlds%3DfeedSegId-mkId-mdId%26zc%3D95101%26rn%3D0%26PMmt%3D1-1-0%26sf2Dir%3DASC%26sf1Nm%3Dprice%26sf2Nm%3Dlocation%26isDealerGrouping%3Dfalse%26rpp%3D50%26feedSegId%3D28705&aff=national&listType=1");
		System.out.println(vh.getMake());
	}

}
