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
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.cts.app.data.ExtractDataHtmlHelper;
import com.cts.app.data.Vehicle;
import com.cts.app.parser.util.ParserConstants;

public class AutotraderItemParser implements ItemParser{

	@Override
	public Vehicle parseItem(String url) throws Exception {
		Vehicle vehicle = new Vehicle();
		Parser parser = null;
		try {

			/*
			 * if (autoTraderHtml == null) { autoTraderHtml =
			 * "http://www.autotrader.com/fyc/vdp.jsp?car_id=259630466&dealer_id=63891641&ct=p"; }
			 */

			parser = new Parser(url);
			NodeList nodeList = parser.parse(null);
			// extract html
			nodeList = extract(nodeList, new TagNameFilter(
					ParserConstants.HTML));
			// extract body
			nodeList = extract(nodeList, new TagNameFilter(
					ParserConstants.BODY));

			// extract all divs from body node
			NodeList divs = extract(nodeList, new TagNameFilter(
					ParserConstants.DIV));
			
			NodeList spans = extract(nodeList, new TagNameFilter(
					ParserConstants.SPAN));

			// extract div id="vehicle-info" from the extracted div's
			NodeList vehicleInfo = extract(divs, new HasAttributeFilter(
					ParserConstants.CLASS,
					"page-header"));
			NodeList priceInfo = extract(spans, new HasAttributeFilter(
					ParserConstants.CLASS,
					"primary-price"));
			StringBuffer vehicleTitleStBuff = new StringBuffer();
			
			if (vehicleInfo.elements().hasMoreNodes()) {
				vehicleTitleStBuff.append(vehicleInfo.elements().nextNode().getChildren().elementAt(0).getChildren().elementAt(0).getText());
			}
			if (priceInfo.elements().hasMoreNodes()) {
				vehicleTitleStBuff.append(" ");
				vehicleTitleStBuff.append(priceInfo.elements().nextNode()
						.toPlainTextString());
			}
			String vehicleTitle = ExtractDataHtmlHelper
					.formatText(ExtractDataHtmlHelper.removeLineBreaks(vehicleTitleStBuff.toString()));
			
			
			// System.out.println("vehicleTitle:"+vehicleTitle);
			vehicle.setVehicleTitle(vehicleTitle);

			// extract div id="vehicle-info" from the extracted div's
			
			String price = "";
			if (priceInfo.elements().hasMoreNodes()) {
				price = priceInfo.elements().nextNode().toPlainTextString();
			}
			// System.out.println("vehicleTitle:"+vehicleTitle);
			vehicle.setPrice(ExtractDataHtmlHelper.formatText(price));

			NodeList allTables = extract(nodeList, new TagNameFilter(
					ParserConstants.TABLE));
			NodeList vehicleContainerTable = extract(allTables, new HasAttributeFilter(
					ParserConstants.CLASS,
					"vehicle-stats"));
			NodeList vehicleContainerTrs = extract(vehicleContainerTable
					.elementAt(0).getChildren(), new TagNameFilter(
					ParserConstants.TR));
			SimpleNodeIterator iterator = vehicleContainerTrs.elements();
			while (iterator.hasMoreNodes()) {
				Node containerTr = iterator.nextNode();
				if (containerTr.getChildren().elementAt(0).getChildren().elementAt(0).toPlainTextString()
						.equalsIgnoreCase(ParserConstants.A_MILEAGE)) {
					if (containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString() != null) {
						String mileage = ExtractDataHtmlHelper
								.formatText(containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString());
						vehicle.setMileage(mileage);
					}
				} else if (containerTr.getChildren().elementAt(0).getChildren().elementAt(0).toPlainTextString().equalsIgnoreCase(
								ParserConstants.A_TRANSMISSION)) {
					if (containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString() != null) {
						String transmission = ExtractDataHtmlHelper
								.formatText(containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString());
						
							if (transmission.indexOf("auto") != -1
									|| transmission.indexOf("Auto") != -1
									|| transmission.indexOf("AUTO") != -1)
								vehicle.setTransmission(ParserConstants.AUTOMATIC);
							else if (transmission.indexOf("manua") != -1
									|| transmission.indexOf("Manua") != -1
									|| transmission.indexOf("MANUA") != -1)
								vehicle.setTransmission(ParserConstants.MANUAL);
						}
				} /*else if (containerTr.getChildren().elementAt(0).getChildren().elementAt(0).toPlainTextString().equalsIgnoreCase(
								ParserConstants.A_BODY_STYLE)) {
					if (containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString() != null) {
						String bodyStyle = ExtractDataHtmlHelper
								.formatText(containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString());
						Collection<String> bodyTypes = vehicle.getAllBodyTypes().values();
						for (String body : bodyTypes) {
							if (body.equalsIgnoreCase(bodyStyle)) {
								vehicle.setBodyType(body);
								break;
							} else
								vehicle.setBodyType(ParserConstants.OTHER);
						}
					}
				}*/ else if (containerTr.getChildren().elementAt(0).getChildren().elementAt(0).toPlainTextString().equalsIgnoreCase(ParserConstants.A_EXTERIOR_COLOR)) {
					if (containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString() != null) {
						String extColor = ExtractDataHtmlHelper.formatText(containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString());
						Collection<String> extColorTypes = vehicle.getAllExteriorColors().values();
						for (String color : extColorTypes) {
							if (color.equalsIgnoreCase(extColor)) {
								vehicle.setExteriorColour(color);
								break;
							} else
								vehicle.setExteriorColour(ParserConstants.OTHER);
						}
					}
				} else if (containerTr.getChildren().elementAt(0).getChildren().elementAt(0).toPlainTextString().equalsIgnoreCase(
								ParserConstants.A_INTERIOR_COLOR)) {
					if (containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString() != null) {
						String intColor = ExtractDataHtmlHelper
								.formatText(containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString());
						Collection<String> intColorTypes = vehicle.getAllInteriorColors().values();
						for (String color : intColorTypes) {
							if (color.equalsIgnoreCase(intColor)) {
								vehicle.setInteriorColour(color);
								break;
							} else
								vehicle.setInteriorColour(ParserConstants.OTHER);
						}
					}
				} else if (containerTr.getChildren().elementAt(0).getChildren().elementAt(0).toPlainTextString().equalsIgnoreCase(
								ParserConstants.A_ENGINE)) {
					if (containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString() != null) {
						String engineSpec = ExtractDataHtmlHelper
								.formatText(containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString());
						Collection<String> cylinders = new Vehicle().getAllCylinders().values();
						for (String cylinder : cylinders) {
							if (engineSpec.toLowerCase().contains(cylinder)) {
								vehicle.setCylinder(cylinder);
								break;
							} else
								vehicle
										.setCylinder(ParserConstants.UNSPECIFIED);

						}
					}
				} /*else if (containerTr.getChildren().elementAt(0).getChildren().elementAt(0).toPlainTextString().equalsIgnoreCase(
								ParserConstants.A_FUEL_TYPE)) {
					if (containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString() != null) {
						String fuelType = ExtractDataHtmlHelper
								.formatText(containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString());
						Collection<String> fuelTypes = new Vehicle().getAllFuelTypes().values();
						for (String fuel : fuelTypes) {
							if (fuel.equalsIgnoreCase(fuelType)) {
								vehicle.setFuelType(fuel);
								break;
							} else
								vehicle
										.setFuelType(ParserConstants.OTHER);

						}
					}
				} */else if (containerTr.getChildren().elementAt(0).getChildren().elementAt(0).toPlainTextString().equalsIgnoreCase(
								ParserConstants.A_VIN)) {
					if (containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString() != null) {
						String vin = ExtractDataHtmlHelper
								.formatText(containerTr.getChildren().elementAt(1).getChildren().elementAt(0).toPlainTextString());
						vin = vin.replaceAll(" ", "");
						if (vin.length() == 17)
							vehicle.setVin(vin);
					}
				}

			}

			NodeList picInfo = extract(divs, new HasAttributeFilter(
					ParserConstants.CLASS,
					ParserConstants.PHOTOS_CONTAINER));
			NodeList tables = extract(picInfo, new TagNameFilter(
					ParserConstants.A));
			SimpleNodeIterator it = tables.elements();
			List<String> images = new ArrayList<String>();
			while (it.hasMoreNodes()) {
				Node n = it.nextNode();
				ImageTag imgN = (ImageTag) n.getFirstChild();
				if (imgN != null) {
					String imgL = imgN.getImageURL();
					imgL = imgL.replaceAll(ParserConstants.SCALAR_80_60,
							ParserConstants.IMAGES);
					imgL = imgL.replaceAll(
							ParserConstants.SCALAR_240_180,
							ParserConstants.IMAGES);
					imgL = imgL.replaceAll(
							ParserConstants.SCALAR_160_120,
							ParserConstants.IMAGES);
					imgL = imgL.replaceAll(ParserConstants.IMG_80_60,
							ParserConstants.IMG_565_421);
					imgL = imgL.replaceAll(ParserConstants.IMG_240_180_A,
							ParserConstants.IMG_565_421_A);
					imgL = imgL.replaceAll(ParserConstants.IMG_160_120,
							ParserConstants.IMG_565_421);
					images.add(imgL);
				}
			}
			vehicle.setImages(images);

			NodeList userContentNode = extract(divs, new HasAttributeFilter(
					ParserConstants.ID,
					"j_id634922276_c1e6d2a"));
			NodeList userContent = extract(userContentNode, new TagNameFilter(
					"p"));
			String descriptionTxt = "";
			if (userContent.elements().hasMoreNodes()) {
				descriptionTxt = userContent.elements().nextNode().getChildren().elementAt(0).getChildren().elementAt(0)
						.toPlainTextString();
			}
			vehicle.setDescription(ExtractDataHtmlHelper
					.formatText(descriptionTxt));

			// try to set the make
			ExtractDataHtmlHelper.extractMakeModel(vehicle, vehicleTitle);

			// get year from header if not present then from desc
			String year = extractYear(vehicleTitle);
			vehicle.setYear(year);

			// get title
			vehicle.setTitleStatus(extractTitle(vehicleTitle, descriptionTxt));

		} catch (Exception e) {
			throw e;
		}
		return vehicle;
	}
	
	private String extractTitle(String header, String plainTextDesc) {
		String cartitle = extractTitle(header, plainTextDesc,
				ParserConstants.CLEAN);
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

	private static NodeList extract(NodeList nodeList, NodeFilter filter) {
		return nodeList.extractAllNodesThatMatch(filter, true);
	}
	
	public static void main(String args[]) throws Exception {
		AutotraderItemParser ca = new AutotraderItemParser();
		Vehicle vh = ca.parseItem("http://www.autotrader.com/cars-for-sale/vehicledetails.xhtml?zip=95126&endYear=2014&showcaseOwnerId=0&startYear=1981&makeCode1=NISSAN&sellerTypes=b&searchRadius=25&bodyStyleCodes=SEDAN&mmt=[NISSAN[][]]&listingId=347157724&listingIndex=7&Log=0");
		System.out.println(vh.getMake());
	}

}
