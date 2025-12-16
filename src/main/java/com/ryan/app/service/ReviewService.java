package com.ryan.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryan.app.dao.RestaurantDAO;
import com.ryan.app.dto.RestaurantReviewsTO;
import com.ryan.app.dto.RestaurantSearchTO;
import com.ryan.app.dto.ReviewDetailTO;
import com.ryan.app.model.Restaurant;
import com.ryan.app.model.Review;
import com.ryan.app.model.factory.ModelFactory;

@Service
public class ReviewService {

	@Autowired
	private RestaurantDAO restaurantDAO;

	@Transactional
	public List<Restaurant> sortAndFilterReviews(RestaurantSearchTO searchTO) {
		List<Restaurant> restaurantList = restaurantDAO.getRestaurantsByReviews(
				searchTO.getRestName(), searchTO.getCuisine(), searchTO.getNear(),
				searchTO.getSortOption(), searchTO.getFilterOption());
		
		return restaurantList;
	}

	public RestaurantReviewsTO getReviews(Integer restaurantId) {
		Restaurant restaurant = restaurantDAO.findRestaurantById(restaurantId);
		List<Review> reviews = restaurantDAO.getRestaurantReviews(restaurantId);
		RestaurantReviewsTO reviewTo = new RestaurantReviewsTO(restaurant, reviews);
		
		return reviewTo;
	}

	public void postReview(ReviewDetailTO reviewDetailTo) {		
		Review review = ModelFactory.getInstance().createReview(null);
		review.setRating(reviewDetailTo.getRating());
		review.setRestaurantId(reviewDetailTo.getRestaurantId());
		review.setUserId(reviewDetailTo.getUserId());
		review.setComments(reviewDetailTo.getComments());
		
		restaurantDAO.insertReview(review);
		
		return;
	}

}
