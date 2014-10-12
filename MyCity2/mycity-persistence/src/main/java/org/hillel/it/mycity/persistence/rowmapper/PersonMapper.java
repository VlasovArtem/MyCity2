package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.UserGroup;
import org.hillel.it.mycity.model.entity.User;
import org.springframework.jdbc.core.RowMapper;

public class PersonMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User person = new User(rs.getString("email"), rs.getString("password"));
		person.setId(rs.getInt("id"));
		person.setLastName(rs.getString("lastname"));
		person.setFirstName(rs.getString("firstname"));
		person.setUsername(rs.getString("username"));
		person.setGroup(UserGroup.valueOf(rs.getString("group")));
		return person;
	}

}
