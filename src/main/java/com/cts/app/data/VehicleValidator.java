package com.cts.app.data;

import java.util.ArrayList;
import java.util.List;

import com.cts.app.parser.util.Errors;

public class VehicleValidator {

	public static Errors validateVehicle(Vehicle veh) {
		Errors errors = new Errors();

		if (veh.getVehicleTitle() == null
				|| veh.getVehicleTitle().length() == 0)
			errors.setIsTitleValid(false);

		if (veh.getMake() == null || "any".equalsIgnoreCase(veh.getMake()))
			errors.setIsMakeValid(false);

		if (veh.getModel() == null || "any".equalsIgnoreCase(veh.getModel()))
			errors.setIsModelValid(false);

		try {
			if (veh.getYear() == null || veh.getYear().length() != 4
					|| Long.valueOf(veh.getYear()) < 1900)
				errors.setIsYearValid(false);
		} catch (NumberFormatException e) {
			errors.setIsYearValid(false);
		}

		try {
			if (veh.getZip() == null || veh.getZip().length() != 5
					|| Long.valueOf(veh.getZip()) < 1)
				errors.setIsZipValid(false);
		} catch (NumberFormatException e) {
			errors.setIsZipValid(false);
		}

		try {
			if (veh.getPrice() == null || veh.getPrice().length() <= 1
					|| Float.valueOf(veh.getPrice()) < 1)
				errors.setIsPriceValid(false);
		} catch (NumberFormatException e) {
			errors.setIsPriceValid(false);
		}

		if (veh.getVin() == null || !VIN.isVinValid(veh.getVin()))
			errors.setIsVINValid(false);

		try {
			if (veh.getMileage() == null || veh.getMileage().length() == 0
					|| Long.valueOf(veh.getMileage()) < 0)
				errors.setIsMileageValid(false);
		} catch (NumberFormatException e) {
			errors.setIsMileageValid(false);
		}

		if (veh.getDescription() == null || veh.getDescription().length() == 0)
			errors.setIsDescriptionValid(false);

		if (veh.getImages() == null || veh.getImages().size() == 0) {
			errors.setIsImagesValid(false);
		} else {
			List<String> images= new ArrayList<String>();
			for(String img : veh.getImages()){
				if(img.length()>0){
					images.add(img);
				}
			}
			veh.setImages(images);
			if(veh.getImages().size() == 0){
				errors.setIsImagesValid(false);
			}
		}
		
		if (veh.getTitleStatus() == null || "other".equalsIgnoreCase(veh.getTitleStatus()) || "".equalsIgnoreCase(veh.getTitleStatus()))
			errors.setIsTitleStatusValid(false);


		errors.setIsErrorPresent(isErrorPresent(errors));
		return errors;
	}

	public static Vehicle cleanupVehicle(Vehicle veh) {
		if (veh.getYear() != null) {
			if (veh.getYears().get(veh.getYear()) == null) {
				veh.setYear("-");
			}
		} else {
			veh.setYear("-");
		}

		if (veh.getTitleStatus() != null) {
			if (veh.getAllTitleStatus().get(veh.getTitleStatus()) == null) {
				veh.setTitleStatus("Other");
			}
		} else {
			veh.setTitleStatus("Other");
		}

		if (veh.getTransmission() != null) {
			if (veh.getTransmissions().get(veh.getTransmission()) == null) {
				veh.setTransmission("Unspecified");
			}
		} else {
			veh.setTransmission("Unspecified");
		}

		if (veh.getCylinder() != null) {
			if (veh.getAllCylinders().get(veh.getCylinder()) == null) {
				veh.setCylinder("Unspecified");
			}
		} else {
			veh.setCylinder("Unspecified");
		}

		if (veh.getExteriorColour() != null) {
			if (veh.getAllExteriorColors().get(veh.getExteriorColour()) == null) {
				veh.setExteriorColour("Other");
			}
		} else {
			veh.setExteriorColour("Other");
		}

		if (veh.getInteriorColour() != null) {
			if (veh.getAllInteriorColors().get(veh.getInteriorColour()) == null) {
				veh.setInteriorColour("Other");
			}
		} else {
			veh.setInteriorColour("Other");
		}

		if (veh.getCondition() != null) {
			if (veh.getAllConditions().get(veh.getCondition()) == null) {
				veh.setCondition("Used");
			}
		} else {
			veh.setCondition("Used");
		}

		return veh;

	}

	private static boolean isErrorPresent(Errors err) {
		boolean isErrorPresent = true;
		if (err.getIsDescriptionValid() && err.getIsImagesValid()
				&& err.getIsMakeValid() && err.getIsMileageValid()
				&& err.getIsModelValid() && err.getIsPriceValid()
				&& err.getIsTitleValid() && err.getIsVINValid() && err.getIsYearValid()
				&& err.getIsZipValid() && err.getIsTitleStatusValid()) {
			isErrorPresent = false;
		}
		return isErrorPresent;
	}
}
