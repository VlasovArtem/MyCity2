package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Restaurant;
import org.springframework.jdbc.core.RowMapper;

public class RestaurantMapper implements RowMapper<Restaurant>{

	@Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Restaurant restaurant = new Restaurant();
		restaurant.setTimeOpen(rs.getTime("time_open").toLocalTime().getHour(), 
				rs.getTime("time_open").toLocalTime().getMinute());
		restaurant.setTimeClose(rs.getTime("time_close").toLocalTime().getHour(), 
				rs.getTime("time_close").toLocalTime().getMinute());
		restaurant.setAverageCheck(rs.getInt("average_check"));
		return restaurant;
	}
	

}
