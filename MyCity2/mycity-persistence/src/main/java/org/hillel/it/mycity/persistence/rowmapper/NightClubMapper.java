package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.NightClub;
import org.springframework.jdbc.core.RowMapper;

public class NightClubMapper implements RowMapper<NightClub>{
	@Override
	public NightClub mapRow(ResultSet rs, int rowNum) throws SQLException {
		NightClub nightClub = new NightClub();
		nightClub.setTimeOpen(rs.getTime("time_open").toLocalTime());
		nightClub.setTimeClose(rs.getTime("time_close").toLocalTime());
		nightClub.setAverageCheck(rs.getInt("average_check"));
		return nightClub;
	}

}
