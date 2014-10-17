package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.persistence.rowmapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class RestaurantDao implements GenericDao<Restaurant>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private EstablishmentDao establishmentDao;
	private AssessmentDao assessmentDao;
	public RestaurantDao() {
		establishmentDao = new EstablishmentDao();
		assessmentDao = new AssessmentDao();
	}
	public void create(Restaurant restaurant) throws SQLException {
		establishmentDao.create(restaurant);
		String insert = "INTSERT INTO restaurant (establishment_id, "
				+ "time_open, time_close, average_check) VALUES (?,?,?,?)";
		jdbcTemplate.update(insert, establishmentDao.getLastId(), 
				restaurant.getTimeOpen(), 
				restaurant.getTimeClose(), 
				restaurant.getAverageCheck());
		for(Assessment assessment : restaurant.getAssessmentsOfEstablishment()) {
			assessmentDao.create(assessment, "restaurant", restaurant.getId());
		}
	}
	public Restaurant read(int id) throws SQLException {
		String read = "SELECT * FROM restaurant WHERE restaurant_id = " + id;
		String select = "SELECT establishment_id FROM restaurant WHERE restaurant.restaurant_id = " + id;
		Restaurant restaurant = jdbcTemplate.queryForObject(read, new RestaurantMapper());
		establishmentDao.read(restaurant, jdbcTemplate.queryForObject(select, Integer.class));
		restaurant.setAssessmentsOfEstablishment(assessmentDao.readAll(restaurant));
		return restaurant;
	}
	public void update(Restaurant restaurant) throws DataAccessException, SQLException {
		String update = "UPDATE restaurant SET time_open = :time_open, ";
		String select = "SELECT establishment_id FROM restaurant WHERE restaurant_id = " + restaurant.getId();
		establishmentDao.update(restaurant, jdbcTemplate.queryForObject(select, Integer.class));
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("time_open", restaurant.getTimeOpen());
		source.addValue("time_close", restaurant.getTimeClose());
		source.addValue("average_check", restaurant.getAverageCheck());
		parameterJdbcTemplate.update(update, source);
		assessmentDao.update(restaurant);
	}
	public void delete(int id) throws DataAccessException, SQLException {
		String delete = "DELETE * FROM restaurant WHERE restaurant_id = " + id;
		String select = "SELECT establishment_id FROM restaurant WHERE restaurant_id = " + id;
		establishmentDao.delete(jdbcTemplate.queryForObject(select, Integer.class));
		jdbcTemplate.update(delete);
		assessmentDao.delete(id);
	}
}
