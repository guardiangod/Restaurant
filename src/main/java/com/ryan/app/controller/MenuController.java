package com.ryan.app.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ryan.app.common.CommonException;
import com.ryan.app.common.Constants;
import com.ryan.app.common.Utils;
import com.ryan.app.dto.MenuDetailTO;
import com.ryan.app.dto.RestaurantSearchTO;
import com.ryan.app.dto.UserSessionTO;
import com.ryan.app.facade.RestaurantFacade;
import com.ryan.app.model.MenuItem;
import com.ryan.app.model.Restaurant;
import com.ryan.app.model.factory.ModelFactory;

@Controller
@SessionAttributes(Constants.SS_USER_INFO)
public class MenuController extends BaseController {

	@Autowired
	private RestaurantFacade restaurantFacade;

	@RequestMapping(value = "/menu/rest/search", method = RequestMethod.GET)
	public String getSearch(Model model) throws CommonException {
		RestaurantSearchTO searchTO = new RestaurantSearchTO();
		restaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		
		return "searchrestmenu";
	}

	@RequestMapping(value = "/menu/rest/result", method = RequestMethod.POST)
	public String getSearchResult(@ModelAttribute("restaurantSearchTo") RestaurantSearchTO searchTO, Model model)
			throws CommonException {
		List<Restaurant> restaurantList = restaurantFacade.getRestaurantService().searchRestaurants(searchTO);
		restaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		model.addAttribute("restaurantList", restaurantList);
		
		return "searchrestmenu";
	}

	@RequestMapping(value = "/menu/view", method = RequestMethod.POST)
	public String getMenu(@ModelAttribute(Constants.SS_USER_INFO) UserSessionTO userDetail,
			@RequestParam("rest") Integer restaurantId, Model model) throws CommonException {
		Map<String, List<MenuItem>> menuMap = restaurantFacade.getMenuService().getMenu(restaurantId);
		MenuDetailTO dto = new MenuDetailTO(restaurantId);
		dto.setMenuMap(menuMap);
		
		model.addAttribute("menuDetailTo", dto);
		
		return "viewmenu";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(MenuItem.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				String[] props = text.split(":");
				MenuItem item = null;
				try {
					item = ModelFactory.getInstance().createMenuItem(Utils.parseInt(props[0]));
					item.setItemName(props[1]);
					item.setPrice(Float.parseFloat(props[2]));
					item.setItemCategory(props[3]);
				} catch (CommonException e) {
					item = null;
				}
				if (item != null)
					setValue(item);
			}
		});
	}
}
