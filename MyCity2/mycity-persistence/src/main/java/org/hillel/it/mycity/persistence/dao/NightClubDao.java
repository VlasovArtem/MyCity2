package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import org.hillel.it.mycity.helper.SqlHelper;
import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.persistence.rowmapper.NightClubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class NightClubDao implements GenericDao<NightClub>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private EstablishmentDao establishmentDao;
	private AssessmentDao assessmentDao;
	public NightClubDao() {
		establishmentDao = new EstablishmentDao();
		assessmentDao = new AssessmentDao();
	}
	public void create(NightClub nightClub) throws SQLException {
		establishmentDao.create(nightClub);
		String insert = "INTSERT INTO night_club (establishment_id, "
				+ "time_open, time_close, average_check) VALUES (?,?,?,?)";
		jdbcTemplate.update(insert, establishmentDao.getLastId(), 
				SqlHelper.getSqlTime(nightClub.getTimeOpen()), 
				SqlHelper.getSqlTime(nightClub.getTimeClose()), 
				nightClub.getAverageCheck());
		for(Assessment assessment : nightClub.getAssessmentsOfEstablishment()) {
			assessmentDao.create(assessment, "night_club", nightClub.getId());
		}
	}
	public NightClub read(int id) throws SQLException {
		String read = "SELECT * FROM night_club WHERE night_club_id = " + id;
		String select = "SELECT establishment_id FROM night_club WHERE night_club.night_club_id = " + id;
		NightClub nightClub = jdbcTemplate.queryForObject(read, new NightClubMapper());
		establishmentDao.read(nightClub, jdbcTemplate.queryForObject(select, Integer.class));
		List<Assessment> assessments = assessmentDao.readAll(nightClub);
		nightClub.setAssessmentsOfEstablishment(assessments);
		return nightClub;
	}
	public void update(NightClub nightClub) throws DataAccessException, SQLException {
		String update = "UPDATE night_club SET time_open = :time_open, ";
		String select = "SELECT establishment_id FROM night_club WHERE night_club_id = " + nightClub.getId();
		establishmentDao.update(nightClub, jdbcTemplate.queryForObject(select, Integer.class));
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("time_open", SqlHelper.getSqlTime(nightClub.getTimeOpen()));
		source.addValue("time_close", SqlHelper.getSqlTime(nightClub.getTimeClose()));
		source.addValue("average_check", nightClub.getAverageCheck());
		parameterJdbcTemplate.update(update, source);
		assessmentDao.update(nightClub);
	}
	public void delete(int id) throws DataAccessException, SQLException {
		String delete = "DELETE * FROM night_club WHERE night_club_id = " + id;
		String select = "SELECT establishment_id FROM night_club WHERE night_club_id = " + id;
		establishmentDao.delete(jdbcTemplate.queryForObject(select, Integer.class));
		jdbcTemplate.update(delete);
		assessmentDao.delete(id);
	}
	
}
