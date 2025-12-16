package com.ryan.app.dao;

import static com.ryan.app.common.Constants.SQL_PERCENT;
import static com.ryan.app.common.Utils.isNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ryan.app.common.Utils;
import com.ryan.app.model.Cuisine;
import com.ryan.app.model.Member;
import com.ryan.app.model.MenuItem;
import com.ryan.app.model.Restaurant;
import com.ryan.app.model.Review;

@Repository
public class RestaurantDAO {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	private NamedParameterJdbcTemplate getJdbcTemplate() {
		return restaurantRepository.getNamedParameterJdbcTemplate();
	}
	
	private String getQuery(String queryName) {
		return restaurantRepository.getQuery(queryName);
	}
	
	public List<Cuisine> getCuisineList() {
		String sql = "query.cuisine.select";
		return getJdbcTemplate().getJdbcOperations().query(getQuery(sql), new Cuisine().map());
	}

	public List<Restaurant> searchRestaurants(String restName, Integer cuisineId, String location) {
		List<Restaurant> restaurantList = new ArrayList<>();

		String sql = "query.restaurant.search";
		Map<String, Object> parameters = new HashMap<>();
		if (!isNull(restName)) {
			parameters.put("restName", SQL_PERCENT.concat(restName).concat(SQL_PERCENT));
		} else {
			parameters.put("restName", SQL_PERCENT);
		}

		if (cuisineId != 0) {
			parameters.put("cuisineId", cuisineId);
		} else {
			parameters.put("cuisineId", SQL_PERCENT);
		}

		if (!isNull(location)) {
			parameters.put("loc", SQL_PERCENT.concat(location).concat(SQL_PERCENT));
			parameters.put("state", SQL_PERCENT.concat(location).concat(SQL_PERCENT));
			parameters.put("postal", SQL_PERCENT.concat(location).concat(SQL_PERCENT));
		} else {
			parameters.put("loc", SQL_PERCENT);
			parameters.put("state", SQL_PERCENT);
			parameters.put("postal", SQL_PERCENT);
		}

		restaurantList = getJdbcTemplate().query(getQuery(sql), parameters, new Restaurant().map());
		return restaurantList;
	}
	
	public List<Restaurant> findRestaurantsByOwner(String ownerId) {
		List<Restaurant> restaurantList = new ArrayList<>();

		String sql = "query.restaurant.owner";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("ownerId", ownerId);
		restaurantList = getJdbcTemplate().query(getQuery(sql), parameters, new Restaurant().map());
		
		return restaurantList;
	}

	public Restaurant findRestaurantById(Integer restaurantId) {
		String sql = "query.restaurant.pkey";
		SqlParameterSource parameters = new MapSqlParameterSource("restId", restaurantId);

		return getJdbcTemplate().queryForObject(getQuery(sql), parameters, new Restaurant().map());
	}

	public void updateRestaurant(Restaurant restaurant) {
		String sql = "query.restaurant.update";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(restaurant);
		getJdbcTemplate().update(getQuery(sql), parameters);
	}

	public Integer insertRestaurant(Restaurant restaurant) {
		String sql = "query.restaurant.insert";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(restaurant);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(getQuery(sql), parameters, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public Member getMemberRecord(String userName, String pwd) {
		Member member = null;

		String sql = "query.member.select";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", userName);
		parameters.put("pwd", pwd);
		try {
			member = getJdbcTemplate().queryForObject(getQuery(sql), parameters, new Member().map());
		} catch (EmptyResultDataAccessException dae) {
			// return null
		}

		return member;
	}

	public Member getMemberBillingRecord(String userName) {
		String sql = "query.member.billing.select";
		SqlParameterSource parameters = new MapSqlParameterSource("userName", userName);

		return getJdbcTemplate().queryForObject(getQuery(sql), parameters, new Member().mapBilling());
	}

	public boolean doesUserExists(String userName) {
		boolean exists = false;

		String sql = "query.member.exists.select";
		SqlParameterSource parameter = new MapSqlParameterSource("userName", userName);
		try {
			getJdbcTemplate().queryForObject(getQuery(sql), parameter, Integer.class);
			exists = true;
		} catch (EmptyResultDataAccessException dae) {
			// no record found
			exists = false;
		}

		return exists;
	}

	public void insertMember(Member member) {
		String sql = "query.member.insert";

		SqlParameterSource userParam = new BeanPropertySqlParameterSource(member);
		getJdbcTemplate().update(getQuery(sql), userParam);

		if (!Utils.isNull(member.getCcNumber())) {
			sql = "query.member.billing.update";
			SqlParameterSource creditParam = new BeanPropertySqlParameterSource(member);
			getJdbcTemplate().update(getQuery(sql), creditParam);
		}
	}

	public List<Restaurant> getRestaurantsByReviews(String restName, Integer cuisineId,
			String location, String sortOption, String filterOption) {
		List<Restaurant> restaurantList = new ArrayList<>();

		StringBuilder sql = new StringBuilder(getQuery("query.review.restaurant.select"));
		Map<String, Object> parameters = new HashMap<>();

		if (!isNull(restName)) {
			parameters.put("restName", SQL_PERCENT.concat(restName).concat(SQL_PERCENT));
		} else {
			parameters.put("restName", SQL_PERCENT);
		}

		if (cuisineId != 0) {
			parameters.put("cuisineId", cuisineId);
		} else {
			parameters.put("cuisineId", SQL_PERCENT);
		}

		if (!isNull(location)) {
			parameters.put("loc", SQL_PERCENT.concat(location).concat(SQL_PERCENT));
			parameters.put("state", SQL_PERCENT.concat(location).concat(SQL_PERCENT));
			parameters.put("postal", SQL_PERCENT.concat(location).concat(SQL_PERCENT));
		} else {
			parameters.put("loc", SQL_PERCENT);
			parameters.put("state", SQL_PERCENT);
			parameters.put("postal", SQL_PERCENT);
		}

		if (!isNull(filterOption)) {
			sql.append(" WHERE round(temp.avg_rating) >= :filterOption");
			parameters.put("filterOption", filterOption);
		}

		if (!isNull(sortOption)) {
			sql.append(" ORDER BY ").append(sortOption).append(" DESC");
		}

		restaurantList = getJdbcTemplate().query(sql.toString(), parameters, new Restaurant().map());
		return restaurantList;
	}

	public List<Review> getRestaurantReviews(Integer restaurantId) {
		String sql = "query.review.select";

		SqlParameterSource parameters = new MapSqlParameterSource("restId", restaurantId);

		return getJdbcTemplate().query(getQuery(sql), parameters, new Review().map());
	}

	public void insertReview(Review review) {
		String sql = "query.review.insert";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(review);
		getJdbcTemplate().update(getQuery(sql), parameters);
	}

	public List<MenuItem> getMenu(Integer restaurantId) {
		String sql = "query.menu.select";
		SqlParameterSource parameters = new MapSqlParameterSource("restId", restaurantId);

		return getJdbcTemplate().query(getQuery(sql), parameters, new MenuItem().map());
	}
	
}
