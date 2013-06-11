package com.cts.app.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.cts.app.data.ExtractDataHtmlHelper;
import com.cts.app.data.Vehicle;
import com.cts.app.parser.util.ParserConstants;

public class CraigslistItemParser implements ItemParser{

	@Override
	public Vehicle parseItem(String url) throws Exception {
		Vehicle vehicle = new Vehicle();
		Parser parser = null;
		try {
			parser = new Parser(url);
			NodeList nodeList = parser.parse(null);

			// extract HTML
			nodeList = extract(nodeList, new TagNameFilter(ParserConstants.HTML));
			
			HtmlCleaner cleaner = new HtmlCleaner();
	        CleanerProperties props = cleaner.getProperties();
	        props.setAllowHtmlInsideAttributes(true);
	        props.setAllowMultiWordAttributes(true);
	        props.setRecognizeUnicodeChars(true);
	        props.setOmitComments(true);
	        
	        TagNode tagNode = cleaner.clean(nodeList.toHtml());
	        Object[] myNodes = tagNode.evaluateXPath("//*[@id='postingbody']/text()");
	        String description = myNodes[0].toString();
			// extract BODY
						nodeList = extract(nodeList, new TagNameFilter(ParserConstants.BODY));
			
			// extract header from body node
			NodeList headerNodeList = extract(nodeList, new TagNameFilter(ParserConstants.CR_H2));
			
			nodeList.elementAt(0).getChildren().elementAt(2);
			
			
			String vehicleTitle = "";
			if (headerNodeList.elements().hasMoreNodes()) {
				vehicleTitle = headerNodeList.elements().nextNode().toPlainTextString();
				// get location
				int closingBracketIndex = vehicleTitle.lastIndexOf("(");
				String location = "";
				if (closingBracketIndex > 0) {
					location = vehicleTitle.substring(closingBracketIndex + 1,vehicleTitle.length() - 2).trim();
				}
				vehicle.setLocation(location);

				// get price
				vehicle.setPrice(extractPrice(vehicleTitle));
			}
			vehicle.setVehicleTitle(vehicleTitle);

		//	NodeList sections = extract(nodeList, new TagNameFilter(ParserConstants.SECTION));
			
			// extract all divs from body node
		//	NodeList divs = extract(nodeList, new TagNameFilter(ParserConstants.DIV));


			// extract all images from the page
			NodeList imageNodes = extract(nodeList, new HasAttributeFilter(ParserConstants.ID, ParserConstants.THUMBS));
			NodeList tables = extract(imageNodes, new TagNameFilter(ParserConstants.A));
			SimpleNodeIterator it = tables.elements();
			List<String> images = new ArrayList<String>();
			while (it.hasMoreNodes()) {
				LinkTag n = (LinkTag) it.nextNode();
				images.add(n.getLink());
			}
			vehicle.setImages(images);

			// extract plain text (non html) description
			vehicle.setDescription(description);

			// get transmission
			vehicle.setTransmission(ExtractDataHtmlHelper.extractTransmission(vehicleTitle, description));
			

			// get vehicle title status
			vehicle.setTitleStatus(extractTitleStatus(vehicleTitle, description));
			ExtractDataHtmlHelper.extractMakeModel(vehicle, vehicleTitle);

			// get year from header if not present then from desc
			String year = extractYear(vehicleTitle);
			if ("".equals(year)) {
				year = extractYear(description);
			}
			vehicle.setYear(year);

			// get title
			vehicle.setTitleStatus(extractTitle(vehicleTitle, description));
			StringTokenizer tokens = new StringTokenizer(description," \t\n\r\f:.");
			//get Vin
			vehicle.setVin(extractVin(tokens));
			//get miles
			vehicle.setMileage(extractMiles(tokens));
			//get color
			vehicle.setExteriorColour(ExtractDataHtmlHelper.extractColor(description));
			
			// get cylinders
			vehicle.setCylinder(extractCylinder(description));
		} catch (Exception e) {
			throw e;
		}
		return vehicle;
	}
	
	private String extractTitleStatus(String vehicleTitle, String descriptionText){
		// get vehicle title status
		if (vehicleTitle.toLowerCase().indexOf(ParserConstants.SALVAGE) > 0
				|| descriptionText.toLowerCase().indexOf(ParserConstants.SALVAGE) > 0) {
			return ParserConstants.SALVAGE;
		} else if (vehicleTitle.toLowerCase().indexOf(ParserConstants.CLEAR) > 0
				|| descriptionText.toLowerCase().indexOf(ParserConstants.CLEAR) > 0) {
			return ParserConstants.CLEAR;
		}
		return ParserConstants.OTHER;
	}

	private String extractVin(StringTokenizer tokens){
		while (tokens.hasMoreTokens()) {
			String s = tokens.nextToken();
			if (s.length() == 17 || s.length() == 18) {
				s = s.replace("#", "");
				if (ExtractDataHtmlHelper.isAlphaNumeric(s)) {
					return s;
				}
			}
		}
		return "";	
	}
	
	private String extractMiles(StringTokenizer tokens){
		while (tokens.hasMoreTokens()) {
			String s = tokens.nextToken();
			if (s.length() == 6 || s.length() == 5) {
				try {
					Integer.parseInt(s.replace(",", ""));
					return s;
				} catch (Exception e) {
					//do nothing
				}
			}

		}
		return "";
	}

	private String extractCylinder(String plainTextDesc) {
		plainTextDesc = plainTextDesc.toLowerCase();
		plainTextDesc = plainTextDesc.replaceAll("\\W", "");
		String cylinder = "";
		if (plainTextDesc.indexOf("v4") > 0
				|| plainTextDesc.indexOf("4cyl") > 0
				|| plainTextDesc.indexOf("4cylinder") > 0) {
			cylinder = "4";
		} else if (plainTextDesc.indexOf("v6") > 0
				|| plainTextDesc.indexOf("6-cyl") > 0
				|| plainTextDesc.indexOf("6-cylinder") > 0) {
			cylinder = "6";
		} else if (plainTextDesc.indexOf("v8") > 0){
			cylinder = "8";
			
		}
		return cylinder;
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
	
	private String removeSpecialChars(String content) {
		String val = "";
		for (int i = 0;i<content.length();i++) {
			if (Character.isLetterOrDigit(content.charAt(i))) {
				val += content.charAt(i);
			}
		}
		return val;
	}

	private int findNextNumericPos(String content) {
		for (int i = 0; i < 20; i++) {
			if (Character.isDigit(content.charAt(i))) {
				return i;
			}
		}
		return -1;
	}
	
	private String extractPrice(String content) {
		//find the position of $
		int dollarIndex = content.indexOf("$");
		if (dollarIndex == -1) {
			return "";
		}
		//find numeric postion after $
		int start = findNextNumericPos(content.substring(dollarIndex));
		if (start == -1) {
			return "";
		}
		
		String value = content.substring(dollarIndex + start);
		for (int i = 0; i < value.length(); i++) { 
			if (value.charAt(i)==',') {//Price can have ,
				continue;
			}
			if (!Character.isDigit(value.charAt(i))) { //read till a non digit is found
				value = value.substring(0, i + 1);
				break;
			}
		}

		try {
			value = removeSpecialChars(value); //remove special characters like , if present.
			Integer.parseInt(value);
			return value;
		} catch (Exception e) {
		}
		return "";
	}
	
	public static void main(String args[]) throws Exception {
		CraigslistItemParser ca = new CraigslistItemParser();
		Vehicle vh = ca.parseItem("http://sfbay.craigslist.org/sfc/cto/3862925971.html");
		System.out.println(vh.getMake());
	}

}
