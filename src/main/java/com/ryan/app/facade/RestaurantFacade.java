package com.ryan.app.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.app.common.CommonException;
import com.ryan.app.dto.RestaurantSearchTO;
import com.ryan.app.model.Cuisine;
import com.ryan.app.model.Member;
import com.ryan.app.service.LoginService;
import com.ryan.app.service.MenuService;
import com.ryan.app.service.RestaurantService;
import com.ryan.app.service.ReviewService;

@Component
public class RestaurantFacade {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private MenuService menuService;
		
	public RestaurantSearchTO populateCuisines(RestaurantSearchTO searchTO) throws CommonException {
		List<Cuisine> cuisineList = restaurantService.getCuisineList();
		searchTO.setCuisineList(cuisineList);
		
		return searchTO;
	}

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public LoginService getLoginService() {
		return loginService;
	}
	
	public ReviewService getReviewService() {
		return reviewService;
	}
	
	public MenuService getMenuService() {
		return menuService;
	}
	
	public Member getUserBillingInfo(String userName) throws CommonException {
		return loginService.getUserBillingInfo(userName);
	}
	
}
