package com.ryan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ryan.app.common.CommonException;
import com.ryan.app.common.Constants;
import com.ryan.app.dto.RestaurantEditTO;
import com.ryan.app.dto.UserSessionTO;
import com.ryan.app.facade.RestaurantFacade;
import com.ryan.app.model.Restaurant;

@Controller
@SessionAttributes(Constants.SS_USER_INFO)
public class OwnershipController extends BaseController {

	@Autowired
	private RestaurantFacade restaurantFacade;
	
	@RequestMapping(value = "/owner/restaurant", method = RequestMethod.GET)
	public String getRestaurantByOwner(@ModelAttribute(Constants.SS_USER_INFO) UserSessionTO userDetail, Model model) 
			throws CommonException {
		List<Restaurant> restaurantList = 
				restaurantFacade.getRestaurantService().findRestaurantsByOwner(userDetail.getUserName());
		model.addAttribute("restaurantList", restaurantList);
		
		return "myrestaurant";
	}

	@RequestMapping(value = "/owner/restaurant/edit", method = RequestMethod.POST)
	public String getRestaurantForEdit(@ModelAttribute(Constants.SS_USER_INFO) UserSessionTO userDetail,
			@RequestParam("restId") Integer restaurantId, Model model) throws CommonException {
		RestaurantEditTO dto = restaurantFacade.getRestaurantService().getRestaurantDetail(restaurantId);
		model.addAttribute("restaurantEditTo", dto);
		
		return "editrestaurant";
	}
	
	@RequestMapping(value = "/owner/restaurant/update", method = RequestMethod.POST)
	public @ResponseBody String updateRestaurant(@ModelAttribute(Constants.SS_USER_INFO) UserSessionTO userDetail,
			@ModelAttribute("restaurantEditTo") RestaurantEditTO restaurantEditTo) throws CommonException {
		Restaurant restaurant = restaurantEditTo.getRestaurant();
		restaurant.setOwnerId(userDetail.getUserName());
		
		restaurantFacade.getRestaurantService().updateRestaurant(restaurant);

		return "Your restaurant has been updated successfully";
	}

	@RequestMapping(value = "/owner/restaurant/new", method = RequestMethod.GET)
	public String getNewRestaurant(@ModelAttribute(Constants.SS_USER_INFO) UserSessionTO userDetail, 
			Model model) throws CommonException {
		RestaurantEditTO dto = restaurantFacade.getRestaurantService().initNewRestaurantInfo();
		model.addAttribute("restaurantEditTo", dto);
		
		return "newrestaurant";
	}

	@RequestMapping(value = "/owner/restaurant/insert", method = RequestMethod.POST)
	public @ResponseBody String submitNewRestaurant(@ModelAttribute(Constants.SS_USER_INFO) UserSessionTO userDetail,
			@ModelAttribute("restaurantEditTo") RestaurantEditTO restaurantEditTo) throws CommonException {
		Restaurant restaurant = restaurantEditTo.getRestaurant();
		restaurant.setOwnerId(userDetail.getUserName());
		
		restaurantFacade.getRestaurantService().addRestaurant(restaurant);

		return "Your restaurant has been added successfully";
	}
}
