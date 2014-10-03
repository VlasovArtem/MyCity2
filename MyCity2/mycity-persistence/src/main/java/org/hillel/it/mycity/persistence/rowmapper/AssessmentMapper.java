package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Assessment;
import org.springframework.jdbc.core.RowMapper;

public class AssessmentMapper implements RowMapper<Assessment>{

	@Override
	public Assessment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Assessment assessment = new Assessment();
		assessment.setId(rs.getInt("assessment_id"));
		assessment.setAssessment(rs.getInt("assessment"));
		return assessment;
	}
	
}
