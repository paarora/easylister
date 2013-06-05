package com.cts.app.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Vehicle {

	private String url;
	private String location;
	private String titleStatus;
	private String price;
	private String vehicleTitle;
	private List<String> images = new ArrayList<String>();
	private String description;
	private String transmission;
	private String year;
	private String vin;
	private String mileage;
	private String cylinder;
	private String make;
	private String model;
	private String zip;
	private String phoneNumber;
	private String condition;
	private String exteriorColour;
	private String interiorColour;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setLocation(String loc) {
		location = loc;
	}

	public String getLocation() {
		return location;
	}

	public void setTitleStatus(String ts) {
		titleStatus = ts;
	}

	public String getTitleStatus() {
		return titleStatus;
	}

	public void setPrice(String pr) {
		price = pr;
	}

	public String getPrice() {
		return price;
	}

	public void setVehicleTitle(String title) {
		vehicleTitle = title;
	}

	public String getVehicleTitle() {
		return vehicleTitle;
	}

	public void setImages(List<String> img) {
		images = img;
	}

	public List<String> getImages() {
		return images;
	}

	public void setDescription(String dText) {
		description = dText;
	}

	public String getDescription() {
		return description;
	}

	public void setTransmission(String trans) {
		transmission = trans;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setYear(String y) {
		year = y;
	}

	public String getYear() {
		return year;
	}

	public void setVin(String v) {
		vin = v;
	}

	public String getVin() {
		return vin;
	}

	public void setMileage(String m) {
		mileage = m;
	}

	public String getMileage() {
		return mileage;
	}

	public void setCylinder(String c) {
		cylinder = c;
	}

	public String getCylinder() {
		return cylinder;
	}

	public void setMake(String c) {
		make = c;
	}

	public String getMake() {
		return make;
	}

	public void setModel(String c) {
		model = c;
	}

	public String getModel() {
		return model;
	}

	public void setZip(String c) {
		zip = c;
	}

	public String getZip() {
		return zip;
	}

	public void setPhoneNumber(String c) {
		phoneNumber = c;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getExteriorColour() {
		return exteriorColour;
	}

	public void setExteriorColour(String exteriorColour) {
		this.exteriorColour = exteriorColour;
	}

	public String getInteriorColour() {
		return interiorColour;
	}

	public void setInteriorColour(String interiorColour) {
		this.interiorColour = interiorColour;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	Map<String, String> years;

	public Map<String, String> getYears() {
		Map<String, String> yearsSelect = new LinkedHashMap<String, String>();
		for (int i = 1900; i < 2015; i++) {
			yearsSelect.put(Integer.toString(i), Integer.toString(i));
		}
		yearsSelect.put("-", "-");
		return yearsSelect;
	}

	public void setYears(Map<String, String> years) {
		this.years = years;
	}

	Map<String, String> transmissions;

	public Map<String, String> getTransmissions() {
		HashMap<String, String> trsSelect = new HashMap<String, String>();
		trsSelect.put("10427", "Automatic");
		trsSelect.put("10428", "Manual");
		trsSelect.put("-12", "Unspecified");
		return trsSelect;
	}

	public void setTransmissions(Map<String, String> transmissions) {
		this.transmissions = transmissions;
	}

	Map<String, String> allTitleStatus;

	public Map<String, String> getAllTitleStatus() {
		Map<String, String> tStatusSelect = new LinkedHashMap<String, String>();
		tStatusSelect.put("Clear","Clear");
		tStatusSelect.put("Salvage", "Salvage");
		tStatusSelect.put("Other", "Other");
		return tStatusSelect;
	}

	public void setAllTitleStatus(Map<String, String> allTitleStatus) {
		this.allTitleStatus = allTitleStatus;
	}

	Map<String, String> allMakes;

	public Map<String, String> getAllMakes() {
		Map<String, String> makeSelect = new LinkedHashMap<String, String>();
		String[] makeArray = { "Any", "Acura", "Alfa Romeo", "AMC",
				"Aston Martin", "Audi", "Austin", "Austin Healey", "Bentley",
				"BMW", "Bugatti", "Buick", "Cadillac", "Chevrolet", "Chrysler",
				"Citroen", "Cord", "Daewoo", "Datsun", "DeLorean", "DeSoto",
				"Dodge", "Eagle", "Edsel", "Ferrari", "Fiat", "Ford", "Geo",
				"GMC", "Honda", "Hummer", "Hyundai", "Infiniti",
				"International Harvester", "Isuzu", "Jaguar", "Jeep", "Kia",
				"Lamborghini", "Lancia", "Land Rover", "Lexus", "Lincoln",
				"Lotus", "Maserati", "Maybach", "Mazda", "Mercedes-Benz",
				"Mercury", "MG", "Mini", "Mitsubishi", "Nash", "Nissan",
				"Oldsmobile", "Opel", "Packard", "Peugeot", "Plymouth",
				"Pontiac", "Porsche", "Renault", "Rolls-Royce", "Saab",
				"Saturn", "Scion", "Shelby", "Smart", "Studebaker", "Subaru",
				"Suzuki", "Toyota", "Triumph", "Volkswagen", "Volvo", "Willys" };
		for (int i = 0; i < makeArray.length; i++) {
			String makeStr = makeArray[i];
			makeSelect.put(makeStr, makeStr);
		}
		return makeSelect;
	}

	public void setAllMakes(Map<String, String> allMakes) {
		this.allMakes = allMakes;
	}

	Map<String, String> allExteriorColors;

	public Map<String, String> getAllExteriorColors() {
		TreeMap<String, String> eColorSelect = new TreeMap<String, String>();
		eColorSelect.put("Black", "Black");
		eColorSelect.put("Blue", "Blue");
		eColorSelect.put("Brown", "Brown");
		eColorSelect.put("Burgundy", "Burgundy");
		eColorSelect.put("Gold", "Gold");
		eColorSelect.put("Grey", "Grey");
		eColorSelect.put("Green", "Green");
		eColorSelect.put("Orange", "Orange");
		eColorSelect.put("Purple", "Purple");
		eColorSelect.put("Red", "Red");
		eColorSelect.put("Silver", "Silver");
		eColorSelect.put("Tan", "Tan");
		eColorSelect.put("Teal", "Teal");
		eColorSelect.put("White", "White");
		eColorSelect.put("Yellow", "Yellow");
		eColorSelect.put("Other", "Other");
		return eColorSelect;
	}

	public void setAllExteriorColors(Map<String, String> allExteriorColors) {
		this.allExteriorColors = allExteriorColors;
	}

	Map<String, String> allInteriorColors;

	public Map<String, String> getAllInteriorColors() {
		TreeMap<String, String> iColorSelect = new TreeMap<String, String>();
		iColorSelect.put("2288", "Black");
		iColorSelect.put("2289", "Blue");
		iColorSelect.put("2290", "Brown");
		iColorSelect.put("10409", "Burgundy");
		iColorSelect.put("10407", "Gold");
		iColorSelect.put("2291", "Grey");
		iColorSelect.put("2196", "Green");
		iColorSelect.put("2375", "Red");
		iColorSelect.put("2287", "Tan");
		iColorSelect.put("10422", "Teal");
		iColorSelect.put("2317", "White");
		iColorSelect.put("-12", "Other");
		return iColorSelect;
	}

	public void setAllInteriorColors(Map<String, String> allInteriorColors) {
		this.allInteriorColors = allInteriorColors;
	}

	Map<String, String> allCylinders;

	public Map<String, String> getAllCylinders() {
		TreeMap<String, String> cylinderSelect = new TreeMap<String, String>();
		cylinderSelect.put("2", "2");
		cylinderSelect.put("3", "3");
		cylinderSelect.put("4", "4");
		cylinderSelect.put("5", "5");
		cylinderSelect.put("6", "6");
		cylinderSelect.put("8", "8");
		cylinderSelect.put("10", "10");
		cylinderSelect.put("12", "12");
		cylinderSelect.put("Unspecified", "Unspecified");
		return cylinderSelect;

	}

	public void setAllCylinders(Map<String, String> allCylinders) {
		this.allCylinders = allCylinders;
	}

	Map<String, String> allBodyTypes;

	public Map<String, String> getAllBodyTypes() {
		TreeMap<String, String> bodySelect = new TreeMap<String, String>();
		bodySelect.put("-10", "-");
		bodySelect.put("2041", "-");
		bodySelect.put("2048", "Coupe");
		bodySelect.put("10174", "Hatchback");
		bodySelect.put("10401", "Limousine");
		bodySelect.put("2049", "Minivan/Van");
		bodySelect.put("2047", "Pickup truck");
		bodySelect.put("2045", "Sedan");
		bodySelect.put("2042", "SUV");
		bodySelect.put("2043", "Wagon");
		bodySelect.put("-12", "Other");
		return bodySelect;
	}

	public void setAllBodyTypes(Map<String, String> allBodyTypes) {
		this.allBodyTypes = allBodyTypes;
	}

	Map<String, String> allFuelTypes;

	public Map<String, String> getAllFuelTypes() {
		TreeMap<String, String> fuelSelect = new TreeMap<String, String>();
		fuelSelect.put("39825", "Biodiesel");
		fuelSelect.put("47599", "CNG");
		fuelSelect.put("47643", "Diesel");
		fuelSelect.put("39286", "Electric");
		fuelSelect.put("48327", "Ethanol - FFV");
		fuelSelect.put("39827", "Ethanol - FFV");
		fuelSelect.put("47601", "Ethanol - FFV");
		fuelSelect.put("47602", "Steam");
		fuelSelect.put("-12", "Steam");
		return fuelSelect;
	}

	public void setAllFuelTypes(Map<String, String> allFuelTypes) {
		this.allFuelTypes = allFuelTypes;
	}

}
