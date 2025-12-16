package com.ryan.app.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ryan.app.model.Cuisine;
import com.ryan.app.model.factory.ModelFactory;

import org.springframework.jdbc.core.RowMapper;

public class CuisineMapper implements RowMapper<Cuisine> {

	@Override
	public Cuisine mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cuisine cuisine = ModelFactory.getInstance().createCuisine(rs.getInt("cuisine_id"));
		cuisine.setCuisineName(rs.getString("cuisine_name"));
		return cuisine;
	}

}
