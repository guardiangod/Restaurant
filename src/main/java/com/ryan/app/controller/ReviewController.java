package com.ryan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ryan.app.common.CommonException;
import com.ryan.app.common.Constants;
import com.ryan.app.dto.RestaurantReviewsTO;
import com.ryan.app.dto.RestaurantSearchTO;
import com.ryan.app.dto.ReviewDetailTO;
import com.ryan.app.dto.UserSessionTO;
import com.ryan.app.facade.RestaurantFacade;
import com.ryan.app.model.Restaurant;

@Controller
@SessionAttributes(Constants.SS_USER_INFO)
public class ReviewController extends BaseController {

	@Autowired
	private RestaurantFacade restaurantFacade;

	@RequestMapping(value = "/review/search", method = RequestMethod.GET)
	public String getSearch(Model model) throws CommonException {
		RestaurantSearchTO searchTO = new RestaurantSearchTO();
		restaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		return "searchreview";
	}

	@RequestMapping(value = "/review/result", method = RequestMethod.POST)
	public String getSearchResult(
			@ModelAttribute("restaurantSearchTo") RestaurantSearchTO searchTO, Model model)
			throws CommonException {
		List<Restaurant> restaurantList = restaurantFacade.getRestaurantService().searchRestaurants(searchTO);
		restaurantFacade.populateCuisines(searchTO);
		searchTO.setSortOption("");
		searchTO.setFilterOption("");

		model.addAttribute("restaurantSearchTo", searchTO);
		model.addAttribute("restaurantList", restaurantList);
		return "searchreview";
	}

	@RequestMapping(value = "/review/filtersort", method = RequestMethod.POST)
	public String sortAndFilterReviews(
			@ModelAttribute("restaurantSearchTo") RestaurantSearchTO searchTO, Model model)
			throws CommonException {
		List<Restaurant> restaurantList = restaurantFacade.getReviewService().sortAndFilterReviews(
				searchTO);
		restaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		model.addAttribute("restaurantList", restaurantList);
		return "searchreview";
	}

	@RequestMapping(value = "/review/viewall", method = RequestMethod.GET)
	public String getReviews(@RequestParam("rest") Integer restaurantId, Model model)
			throws CommonException {
		RestaurantReviewsTO dto = restaurantFacade.getReviewService().getReviews(restaurantId);

		model.addAttribute("reviewTo", dto);
		return "viewreview";
	}

	@RequestMapping(value = "/review/new", method = RequestMethod.POST)
	public String getNewReview(@ModelAttribute(Constants.SS_USER_INFO) UserSessionTO userDetail,
			@RequestParam("rest") Integer restaurantId, Model model) throws CommonException {
		ReviewDetailTO dto = new ReviewDetailTO();
		dto.setRestaurantId(restaurantId);

		model.addAttribute("reviewDetailTo", dto);
		return "newreview";
	}

	@RequestMapping(value = "/review/new/submit", method = RequestMethod.POST)
	public String submitNewReview(@ModelAttribute(Constants.SS_USER_INFO) UserSessionTO userDetail,
			@ModelAttribute("reviewDetailTo") ReviewDetailTO reviewDetailTo, Model model)
			throws CommonException {
		reviewDetailTo.setUserId(userDetail.getUserName());
		restaurantFacade.getReviewService().postReview(reviewDetailTo);
		String restaurantId = Integer.toString(reviewDetailTo.getRestaurantId());

		return "redirect:/review/viewall?rest=".concat(restaurantId);
	}
}
