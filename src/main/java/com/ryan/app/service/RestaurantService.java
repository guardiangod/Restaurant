package com.ryan.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryan.app.common.CommonException;
import com.ryan.app.dao.RestaurantDAO;
import com.ryan.app.dto.RestaurantEditTO;
import com.ryan.app.dto.RestaurantSearchTO;
import com.ryan.app.model.Cuisine;
import com.ryan.app.model.Restaurant;
import com.ryan.app.model.factory.ModelFactory;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantDAO restaurantDAO;

	@Transactional
	public List<Cuisine> getCuisineList() throws CommonException {
		return restaurantDAO.getCuisineList();
	}
	
	@Transactional
	public List<Restaurant> searchRestaurants(RestaurantSearchTO searchTO) throws CommonException {
		return restaurantDAO.searchRestaurants(searchTO.getRestName(), searchTO.getCuisine(), searchTO.getNear());
	}
	
	@Transactional
	public List<Restaurant> findRestaurantsByOwner(String ownerId) throws CommonException {
		return restaurantDAO.findRestaurantsByOwner(ownerId);
	}
	
	@Transactional
	public Restaurant findRestaurantsById(Integer restaurantId) throws CommonException {
		return restaurantDAO.findRestaurantById(restaurantId);
	}
	
	@Transactional
	public RestaurantEditTO getRestaurantDetail(Integer restaurantId) {
		Restaurant restaurant = restaurantDAO.findRestaurantById(restaurantId);
		
		RestaurantEditTO restEditTO = new RestaurantEditTO(restaurant);
		List<Cuisine> cuisineList = restaurantDAO.getCuisineList();
		restEditTO.setCuisineList(cuisineList);
		
		return restEditTO;
	}

	@Transactional
	public void updateRestaurant(Restaurant restaurant) throws CommonException {
		restaurantDAO.updateRestaurant(restaurant);
	}
	
	@Transactional
	public RestaurantEditTO initNewRestaurantInfo() throws CommonException {
		Restaurant restaurant = ModelFactory.getInstance().createRestaurant(null);
		
		RestaurantEditTO restEditTO = new RestaurantEditTO(restaurant);
		List<Cuisine> cuisineList = restaurantDAO.getCuisineList();
		restEditTO.setCuisineList(cuisineList);
		
		return restEditTO;
	}

	@Transactional
	public Integer addRestaurant(Restaurant restaurant) throws CommonException {
		Integer restaurantId = restaurantDAO.insertRestaurant(restaurant);

		return restaurantId;
	}
}
