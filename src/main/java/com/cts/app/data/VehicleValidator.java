package com.cts.app.data;

import java.util.ArrayList;
import java.util.List;

import com.cts.app.parser.util.ErrorEnum;

public class VehicleValidator {
	
	
	public static List<ErrorEnum> validateVehicle(Vehicle veh){
		List<ErrorEnum> errorList = new ArrayList<ErrorEnum>();
		
		if (veh.getVehicleTitle() == null || veh.getVehicleTitle().length() == 0)
			errorList.add(ErrorEnum.TITLE_INVALID);
		
		if (veh.getMake() == null || "any".equalsIgnoreCase(veh.getMake()))
			errorList.add(ErrorEnum.MAKE_INVALID);
		
		if (veh.getModel() == null || "any".equalsIgnoreCase(veh.getModel()))
			errorList.add(ErrorEnum.MODEL_INVALID);
		
		try {
			if (veh.getYear() == null || veh.getYear().length() != 4 || Long.valueOf(veh.getYear())<1900)
				errorList.add(ErrorEnum.YEAR_INVALID);
		} catch (NumberFormatException e) {
			errorList.add(ErrorEnum.YEAR_INVALID);
		}
		
		try {
			if (veh.getZip() == null || veh.getZip().length() != 5 || Long.valueOf(veh.getZip())<1)
				errorList.add(ErrorEnum.ZIP_INVALID);
		} catch (NumberFormatException e) {
			errorList.add(ErrorEnum.ZIP_INVALID);
		}
		
		if (veh.getVin() == null || !VIN.isVinValid(veh.getVin()))
			errorList.add(ErrorEnum.VIN_INVALID);
		
		try {
		if (veh.getMileage() == null || veh.getMileage().length() == 0 || Long.valueOf(veh.getMileage())<0)
			errorList.add(ErrorEnum.MILEAGE_INVALID);
		} catch (NumberFormatException e) {
			errorList.add(ErrorEnum.MILEAGE_INVALID);
		}
		
		if (veh.getDescription() == null || veh.getDescription().length() == 0)
			errorList.add(ErrorEnum.DESCRIPTION_INVALID);
	
		return errorList;
	}

	
}
