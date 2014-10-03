package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Cinema;
import org.springframework.jdbc.core.RowMapper;

public class CinemaMapper implements RowMapper<Cinema>{

	@Override
	public Cinema mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cinema cinema = new Cinema();
		cinema.setId(rs.getInt("id"));
		cinema.setNumberOfHalls(rs.getInt("number_of_halls"));
		cinema.setNumberOfSeatsInHall(rs.getInt("number_of_seats_in_halls"));
		cinema.setCinemaTechnology(rs.getString("cinema_technology"));
		return cinema;
	}

}
