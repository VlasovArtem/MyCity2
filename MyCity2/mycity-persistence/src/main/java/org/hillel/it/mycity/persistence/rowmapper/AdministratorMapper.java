package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Group;
import org.springframework.jdbc.core.RowMapper;

public class AdministratorMapper implements RowMapper<Administrator>{

	@Override
	public Administrator mapRow(ResultSet rs, int rowNum) throws SQLException {
		Administrator administrator = new Administrator(rs.getString("email"), rs.getString("password"));
		administrator.setId(rs.getInt("person_id"));
		administrator.setUsername(rs.getString("username"));
		administrator.setFirstName(rs.getString("firstname"));
		administrator.setLastName(rs.getString("lastname"));
		administrator.setGroup(Group.Administrator);
		administrator.setCreateDate(rs.getDate("created_date"));
		administrator.setModifiedDate(rs.getDate("modified_date"));
		Administrator test = new Administrator("test", "test@mail.com");
		test.setId(rs.getInt("created_by"));
		administrator.setCreatedBy(test);
		Administrator test1 = new Administrator("test", "test@mail.com");
		test1.setId(rs.getInt("modified_by"));
		administrator.setModifiedBy(test1);
		return administrator;
	}

}
