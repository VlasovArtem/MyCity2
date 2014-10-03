package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Group;
import org.hillel.it.mycity.model.entity.Person;
import org.springframework.jdbc.core.RowMapper;

public class PersonMapper implements RowMapper<Person>{

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person(rs.getString("email"), rs.getString("password"));
		person.setId(rs.getInt("id"));
		person.setLastName(rs.getString("lastname"));
		person.setFirstName(rs.getString("firstname"));
		person.setUsername(rs.getString("username"));
		person.setGroup(Group.valueOf(rs.getString("group")));
		return person;
	}

}
