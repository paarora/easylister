package com.cts.app.data;

import java.util.ArrayList;
import java.util.List;

import com.cts.app.parser.util.ErrorEnum;

public class VehicleValidator {

	public static List<ErrorEnum> validateVehicle(Vehicle veh) {
		List<ErrorEnum> errorList = new ArrayList<ErrorEnum>();

		if (veh.getVehicleTitle() == null
				|| veh.getVehicleTitle().length() == 0)
			errorList.add(ErrorEnum.TITLE_INVALID);

		if (veh.getMake() == null || "any".equalsIgnoreCase(veh.getMake()))
			errorList.add(ErrorEnum.MAKE_INVALID);

		if (veh.getModel() == null || "any".equalsIgnoreCase(veh.getModel()))
			errorList.add(ErrorEnum.MODEL_INVALID);

		try {
			if (veh.getYear() == null || veh.getYear().length() != 4
					|| Long.valueOf(veh.getYear()) < 1900)
				errorList.add(ErrorEnum.YEAR_INVALID);
		} catch (NumberFormatException e) {
			errorList.add(ErrorEnum.YEAR_INVALID);
		}

		try {
			if (veh.getZip() == null || veh.getZip().length() != 5
					|| Long.valueOf(veh.getZip()) < 1)
				errorList.add(ErrorEnum.ZIP_INVALID);
		} catch (NumberFormatException e) {
			errorList.add(ErrorEnum.ZIP_INVALID);
		}
		
		try {
			if (veh.getPrice() == null || veh.getPrice().length() <= 1
					|| Float.valueOf(veh.getPrice()) < 1)
				errorList.add(ErrorEnum.ZIP_INVALID);
		} catch (NumberFormatException e) {
			errorList.add(ErrorEnum.ZIP_INVALID);
		}
		

		if (veh.getVin() == null || !VIN.isVinValid(veh.getVin()))
			errorList.add(ErrorEnum.VIN_INVALID);

		try {
			if (veh.getMileage() == null || veh.getMileage().length() == 0
					|| Long.valueOf(veh.getMileage()) < 0)
				errorList.add(ErrorEnum.MILEAGE_INVALID);
		} catch (NumberFormatException e) {
			errorList.add(ErrorEnum.MILEAGE_INVALID);
		}

		if (veh.getDescription() == null || veh.getDescription().length() == 0)
			errorList.add(ErrorEnum.DESCRIPTION_INVALID);
		
		if (veh.getImages() == null || veh.getImages().size() == 0)
			errorList.add(ErrorEnum.IMAGES_INVALID);

		return errorList;
	}

	public static Vehicle cleanupVehicle(Vehicle veh) {
		if (veh.getYear() != null) {
			if(veh.getYears().get(veh.getYear())==null){
				veh.setYear("-");
			}
		} else {
			veh.setYear("-");
		}
		
		if (veh.getVehicleTitle() != null) {
			if(veh.getAllTitleStatus().get(veh.getVehicleTitle())==null){
				veh.setVehicleTitle("Other");
			}
		} else {
			veh.setVehicleTitle("Other");
		}
		
		if (veh.getTransmission() != null) {
			if(veh.getTransmissions().get(veh.getTransmission())==null){
				veh.setTransmission("Unspecified");
			}
		} else {
			veh.setTransmission("Unspecified");
		}
		
		if (veh.getCylinder() != null) {
			if(veh.getAllCylinders().get(veh.getCylinder())==null){
				veh.setCylinder("Unspecified");
			}
		} else {
			veh.setCylinder("Unspecified");
		}
		
		if (veh.getExteriorColour() != null) {
			if(veh.getAllExteriorColors().get(veh.getExteriorColour())==null){
				veh.setExteriorColour("Other");
			}
		} else {
			veh.setExteriorColour("Other");
		}
		
		if (veh.getInteriorColour() != null) {
			if(veh.getAllInteriorColors().get(veh.getInteriorColour())==null){
				veh.setInteriorColour("Other");
			}
		} else {
			veh.setInteriorColour("Other");
		}
		
		if (veh.getCondition() != null) {
			if(veh.getAllConditions().get(veh.getCondition())==null){
				veh.setCondition("Used");
			}
		} else {
			veh.setCondition("Used");
		}
		
		return veh;

	}

}
