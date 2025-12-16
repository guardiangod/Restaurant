package com.ryan.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryan.app.dao.RestaurantDAO;
import com.ryan.app.model.MenuItem;

@Service
public class MenuService {
	
	@Autowired
	private RestaurantDAO restaurantDAO;

	@Transactional
	public Map<String, List<MenuItem>> getMenu(Integer restaurantId) {
		List<MenuItem> menuItems = restaurantDAO.getMenu(restaurantId);

		HashMap<String, List<MenuItem>> menuMap = new HashMap<>();
		for (MenuItem item : menuItems) {
			List<MenuItem> itemValues = menuMap.get(item.getItemCategory());
			if (itemValues == null) {
				// create new category to add item
				itemValues = new ArrayList<MenuItem>();
			}
			// add item to category
			itemValues.add(item);
			menuMap.put(item.getItemCategory(), itemValues);
		}

		return menuMap;
	}
	
}
