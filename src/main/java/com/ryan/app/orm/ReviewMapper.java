package com.ryan.app.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ryan.app.model.Review;
import com.ryan.app.model.factory.ModelFactory;

import org.springframework.jdbc.core.RowMapper;

public class ReviewMapper implements RowMapper<Review> {
	
	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review review = ModelFactory.getInstance().createReview(rs.getInt("review_number"));
		review.setRestaurantId(rs.getInt("restaurant_id"));
		review.setRating(rs.getInt("rating"));
		review.setComments(rs.getString("comments"));
		review.setUserId(rs.getString("user_id"));
		review.setReviewDateTime(rs.getString("review_datetime"));
		
		return review;
	}

}
