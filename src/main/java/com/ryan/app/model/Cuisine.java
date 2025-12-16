package com.ryan.app.model;

import java.io.Serializable;

import com.ryan.app.orm.CuisineMapper;

import org.springframework.jdbc.core.RowMapper;

public class Cuisine extends BaseModel implements Serializable {

	private static final long serialVersionUID = 4829665876833655322L;

	private Integer cuisineId;

	private String cuisineName;

	public Integer getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(Integer cuisineID) {
		this.cuisineId = cuisineID;
	}

	public String getCuisineName() {
		return cuisineName;
	}

	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}
	
	public RowMapper<Cuisine> map() {
		return new CuisineMapper();
	}

}
