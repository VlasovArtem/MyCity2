package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.persistence.rowmapper.CinemaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class CinemaDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private EstablishmentDao establishmentDao;
	private AssessmentDao assessmentDao;
	public CinemaDao() {
		establishmentDao = new EstablishmentDao();
		assessmentDao = new AssessmentDao();
	}
	public void create(Cinema cinema) throws SQLException {
		establishmentDao.create(cinema);
		String create = "INSERT INTO cinema (establishment_id, number_of_halls, "
				+ "number_of_seats_in_halls, cinema_technology) VALUES(?,?,?,?)";
		jdbcTemplate.update(create, establishmentDao.getLastId(),cinema.getNumberOfHalls(),
				cinema.getNumberOfSeatsInHall(), cinema.getCinemaTechnology());
		for(Assessment assessment : cinema.getAssessmentsOfEstablishment()) {
			assessmentDao.create(assessment, "cinema", cinema.getId());
		}
	}
	public Cinema read(int id) throws SQLException, ClassNotFoundException {
		String read = "SELECT * FROM cinema WHERE cinema_id = :id";
		String select = "SELECT establishment_id FROM cinema WHERE cinema_id = :id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
		Cinema cinema = parameterJdbcTemplate.queryForObject(read, sqlParameterSource, new CinemaMapper());
		establishmentDao.read(cinema, parameterJdbcTemplate.queryForObject
				(select, sqlParameterSource, Integer.class));
		cinema.setAssessmentsOfEstablishment(assessmentDao.readAll(cinema));
		return cinema;
	}
	public void update(Cinema cinema) throws SQLException {
		String update = "UPDATE cinema SET number_of_halls = :number_of_halls, "
				+ "number_of_seats_in_halls = :number_of_seats_in_halls, "
				+ "cinema_technology = :cinema_technology "
				+ "WHERE cinema_id = :id";
		String select = "SELECT establishment_id FROM cinema WHERE cinema_id =" + cinema.getId();
		establishmentDao.update(cinema, jdbcTemplate.queryForObject(select, Integer.class));
		MapSqlParameterSource cinemaSource = new MapSqlParameterSource();
		cinemaSource.addValue("number_of_halls", cinema.getNumberOfHalls());
		cinemaSource.addValue("number_of_seats_in_halls", cinema.getNumberOfSeatsInHall());
		cinemaSource.addValue("cinema_technology", cinema.getCinemaTechnology());
		cinemaSource.addValue("id", cinema.getId());
		parameterJdbcTemplate.update(update, cinemaSource);
		assessmentDao.update(cinema);
		
	}
	public void delete(int id) throws DataAccessException, SQLException {
		String delete = "DELETE FROM cinema WHERE cinema_id = " + id;
		String select = "SELECT establishment_id FROM cinema WHERE cinema_id = " + id;
		establishmentDao.delete(jdbcTemplate.queryForObject(select, Integer.class));
		assessmentDao.delete(id);
		jdbcTemplate.update(delete);
	}
}
