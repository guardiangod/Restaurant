package com.ryan.app.model.factory;

import com.ryan.app.common.Utils;
import com.ryan.app.model.Cuisine;
import com.ryan.app.model.Member;
import com.ryan.app.model.MenuItem;
import com.ryan.app.model.Restaurant;
import com.ryan.app.model.Review;

public final class ModelFactory {
	
	private static final ModelFactory _instance = new ModelFactory();
	
	private ModelFactory() {
		// only exists to thwart instantiation using constructors
	}
	
	public static ModelFactory getInstance() {
		return _instance;
	}

	public Cuisine createCuisine(Integer cuisineId) {
		Cuisine cuisine = new Cuisine();
		cuisine.setCuisineId(cuisineId);

		return cuisine;
	}

	public Restaurant createRestaurant(Integer restaurantId) {
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(restaurantId);
		
		return restaurant;
	}

	public Member createMember(String userId, String pwd) {
		Member member = new Member();
		member.setUserId(Utils.isNull(userId)?"":userId);
		member.setUserPwd(Utils.isNull(pwd)?"":pwd);
		
		return member;
	}
	
	public Review createReview(Integer reviewNumber) {
		Review review = new Review();
		review.setReviewNumber(reviewNumber);
		
		return review;
	}
	
	public MenuItem createMenuItem(Integer itemCode) {
		MenuItem menuItem = new MenuItem();
		menuItem.setItemCode(itemCode);
		
		return menuItem;
	}

}
