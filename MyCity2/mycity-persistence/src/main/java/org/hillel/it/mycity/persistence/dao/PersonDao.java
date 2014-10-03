package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Group;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.rowmapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class PersonDao implements GenericDao<Person>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private BaseEntityDao baseEntityDao;
	public PersonDao() {
		baseEntityDao = new BaseEntityDao(); 
	}
	public void create(Person person) throws SQLException {
		baseEntityDao.create(person);
		String create = "INSERT INTO person (base_entity_id,firstname,lastname,"
				+ "username,email,password,group) VALUES(:base_entity,:firstname,:lastname"
				+ ",:username,:email,:password,:group)";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("base_entity", baseEntityDao.getLastId());
		source.addValue("firstname", person.getFirstName());
		source.addValue("lastname", person.getLastName());
		source.addValue("username", person.getUsername());
		source.addValue("email", person.getEmail());
		source.addValue("password", person.getPassword());
		source.addValue("group", person.getGroup().toString());
		parameterJdbcTemplate.update(create, source);
	}
	public Person read(int id) {
		String read = "SELECT * FROM person WHERE person_id = " + id + " "
				+ "AND group = 'Moderator' OR group = 'User'";
		String select = "SELECT base_entity_id FROM person WHERE person_id = " + id + " "
				+ "AND group = 'Moderator' OR group = 'User'";
		Person person = jdbcTemplate.queryForObject(read, new PersonMapper());
		baseEntityDao.read(person, jdbcTemplate.queryForObject(select, Integer.class));
		if(person.getGroup() == Group.Moderator) {
			return (Moderator) person;
		} else {
			return (User) person;
		}
	}
	public void update(Person person) throws DataAccessException, SQLException {
		String select = "SELECT base_entity_id FROM person WHERE person_id=" + person.getId();
		String create = "UPDATE person SET firstname = :firstname,"
				+ "lastname = :lastname, username = :username, email = :email, "
				+ "password = :password, group = :group WHERE person_id=" + person.getId();
		baseEntityDao.update(person, jdbcTemplate.queryForObject(select, Integer.class));
		SqlParameterSource source = new BeanPropertySqlParameterSource(person);
		parameterJdbcTemplate.update(create, source);
	}
	public void delete(int id) {
		String select = "SELECT base_entity_id FROM person WHERE person_id=" + id;
		String delete = "DELETE * FROM person WHERE person_id = " + id + " AND group = 'Moderator' OR group = 'User'";
		baseEntityDao.delete(jdbcTemplate.queryForObject(select, Integer.class));
		jdbcTemplate.update(delete);
	}
}
