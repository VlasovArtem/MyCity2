package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Establishment;
import org.springframework.jdbc.core.RowMapper;

public class EstablishmentMapper implements RowMapper<Establishment>{
	private Establishment establishment;
	public <T extends Establishment> EstablishmentMapper(T t) {
		establishment = t;
	}

	@Override
	public Establishment mapRow(ResultSet rs, int rowNum) throws SQLException {
		establishment.setName(rs.getString("name"));
		establishment.setAddress(rs.getString("address"));
		try {
			establishment.setTelephone(rs.getString("telephone"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		establishment.setDescription(rs.getString("description"));
		return establishment;
	}

}
