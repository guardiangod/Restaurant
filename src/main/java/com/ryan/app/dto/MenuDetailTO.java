package com.ryan.app.dto;

import java.util.List;
import java.util.Map;

import com.ryan.app.model.MenuItem;

public class MenuDetailTO {

	private Integer restaurantId;
	
	Map<String, List<MenuItem>> menuMap;
	
	public MenuDetailTO(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Map<String, List<MenuItem>> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<String, List<MenuItem>> menuMap) {
		this.menuMap = menuMap;
	}
	
}
