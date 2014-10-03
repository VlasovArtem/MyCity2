package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.persistence.rowmapper.AssessmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class AssessmentDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private BaseEntityDao baseEntityDao;
	public AssessmentDao() {
		baseEntityDao = new BaseEntityDao();
	}
	protected void create(Assessment assessment, String tableName, int establishmentId) throws SQLException {
		String name = tableName + "_assessment";
		String create = "INSERT INTO assessment (base_entity_id, assessment) VALUES(?, ?)";
		String createEstablishmentAssessment = "INSERT INTO " + name + "(" 
				+ tableName + "_id, assessment_id, person_id) VALUES(?,?,?)";
		baseEntityDao.create(assessment);
		jdbcTemplate.update(create, baseEntityDao.getLastId(), assessment.getAssessment());
		jdbcTemplate.update(createEstablishmentAssessment, establishmentId, assessment.getId(), assessment.getCreatedBy().getId());
	}
	protected <T extends Establishment> List<Assessment> readAll(T t) {
		String tableName = t.getClass().getSimpleName().toLowerCase();
		String read = "SELECT * FROM assessment WHERE assessment.assessment_id = " 
				+ tableName + "_assessment.assessment_id AND " + tableName + "_assessment." 
				+ tableName + "_id = " + t.getId();
		List<Assessment> assessments = parameterJdbcTemplate.query(read, new AssessmentMapper());
		for(Assessment assessment : assessments) {
			baseEntityDao.read(assessment, jdbcTemplate.queryForObject("SELECT base_entity_id FROM"
					+ " assessment WHERE assessment_id = " + assessment.getId(), Integer.class));
		}
		return assessments;
	}
	protected Assessment read(int id) {
		String read = "SELECT * FROM assessment WHERE assessment_id = " + id;
		Assessment assessment = jdbcTemplate.queryForObject(read, new AssessmentMapper());
		baseEntityDao.read(assessment, jdbcTemplate.queryForObject("SELECT base_entity_id FROM assessment "
				+ "WHERE assessment_id = " + id, Integer.class));
		return assessment;
	}
	protected <T extends Establishment> void update(T t) throws DataAccessException, SQLException {
		String update = "UPDATE assessment SET assessment.assessment = :assessment WHERE "
				+ "assessment.assessment_id = :assessment_id";
		String select = "SELECT base_entity_id FROM assessment WHERE assessment.assessment_id = :assessment_id";
		for(Assessment assessment : t.getAssessmentsOfEstablishment()) {
			MapSqlParameterSource source = new MapSqlParameterSource();
			source.addValue("assessment", assessment.getAssessment());
			source.addValue("assessment_id", assessment.getId());
			parameterJdbcTemplate.update(update, source);
			baseEntityDao.update(assessment, jdbcTemplate.queryForObject(select, Integer.class));
		}
	}
	protected void delete(int id) {
		String delete = "DELETE * FROM assessment WHERE assessment_id = " + id;
		String select = "SELECT base_entity_id FROM assessment WHERE assessment.assessment_id = " + id;
		baseEntityDao.delete(jdbcTemplate.queryForObject(select, Integer.class));
		jdbcTemplate.update(delete);
	}
}
