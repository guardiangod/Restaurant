package com.ryan.app.dto;

import java.util.List;

import com.ryan.app.model.Cuisine;
import com.ryan.app.model.Restaurant;

public class RestaurantEditTO {

	private Restaurant restaurant;

	private List<Cuisine> cuisineList;

	public RestaurantEditTO() {
		
	}
	
	public RestaurantEditTO(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Cuisine> getCuisineList() {
		return cuisineList;
	}

	public void setCuisineList(List<Cuisine> cuisineList) {
		this.cuisineList = cuisineList;
	}

}
