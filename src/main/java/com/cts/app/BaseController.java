package com.cts.app;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cts.app.data.Vehicle;
import com.cts.app.data.VehicleValidator;
import com.cts.app.ebay.ApiHelper;
import com.cts.app.ebay.ApiRespose;
import com.cts.app.parser.ItemParser;
import com.cts.app.parser.ItemParserFactory;
import com.cts.app.parser.util.ErrorEnum;


@SessionAttributes({"vehicle"})
@Controller
@RequestMapping("/")
public class BaseController {
	
	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";
 
	}
 
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public ModelAndView form(@RequestParam String url, ModelMap model) {
		ModelAndView mav = new ModelAndView("form");
		System.out.println(url);
		session().setAttribute("veh", null);
		ItemParser parser = ItemParserFactory.getInstance().getItemParser(url);
		Vehicle veh = null;
		try {
			veh = parser.parseItem(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ErrorEnum> errors = null;
		if(veh!=null)
			errors = VehicleValidator.validateVehicle(veh);
		mav.addObject("vehicle", veh);
		mav.addObject("error", errors);
		//model.addAttribute("message", "Maven Web Project + Spring 3 MVC - " + name);
		return mav;
 
	}
	
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public ModelAndView form(@ModelAttribute("vehicle") Vehicle veh, ModelMap model) {
		List<ErrorEnum> errors = VehicleValidator.validateVehicle(veh);
		if(errors.size()>0){
			ModelAndView mav = new ModelAndView("form");
			mav.addObject("vehicle", veh);
			mav.addObject("error", errors);
			return mav;
		} else {
			ApiRespose resp = ApiHelper.getInstance().verifyAddItem(veh);
			if(resp.isCallSuccessful()){
				ModelAndView mav = new ModelAndView("confirmfee");
				session().setAttribute("veh", veh);
				mav.addObject("vehicle", veh);
				mav.addObject("fee", resp.getFee());
				return mav;
			} else {
				ModelAndView mav = new ModelAndView("form");
				mav.addObject("vehicle", veh);
				mav.addObject("error", errors);
				return mav;
			}
		}
 
	}
	
	@RequestMapping(value="/confirmfee", method = RequestMethod.POST)
	public ModelAndView confirmFee(@ModelAttribute("vehicle") Vehicle veh, @ModelAttribute("fee") String fee, ModelMap model) {	
		ModelAndView mav = new ModelAndView("confirmfee");
		mav.addObject("vehicle", veh);
		mav.addObject("fee", fee);
		return mav;
	}
	
	@RequestMapping(value="/listed", method = RequestMethod.POST)
	public ModelAndView listed(@ModelAttribute("confirm") String confirm, ModelMap model) {	
		ModelAndView mav = new ModelAndView("listed");
		Vehicle veh = (Vehicle)session().getAttribute("veh");
		ApiRespose resp = ApiHelper.getInstance().addItem(veh);
		mav.addObject("url", resp.getItemUrl());
		return mav;
	}

}
