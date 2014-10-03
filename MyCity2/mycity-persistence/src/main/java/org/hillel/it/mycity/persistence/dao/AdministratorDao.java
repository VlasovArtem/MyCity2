package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.persistence.rowmapper.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class AdministratorDao implements GenericDao<Administrator>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private List<Administrator> administrators;
	private BaseEntityDao baseEntityDao;
	public AdministratorDao() {
		administrators = new ArrayList<>();
		baseEntityDao = new BaseEntityDao();
		try {
			getAdministartors();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void create(Administrator administrator) throws SQLException {
		baseEntityDao.create(administrator);
		String create = "INSERT INTO person (base_entity_id,firstname,lastname,"
				+ "username,email,password,group) VALUES(:base_entity,:firstname,:lastname"
				+ ",:username,:email,:password,:group)";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("base_entity", baseEntityDao.getLastId());
		source.addValue("firstname", administrator.getFirstName());
		source.addValue("lastname", administrator.getLastName());
		source.addValue("username", administrator.getUsername());
		source.addValue("email", administrator.getEmail());
		source.addValue("password", administrator.getPassword());
		source.addValue("group", administrator.getGroup().toString());
		parameterJdbcTemplate.update(create, source);
		administrators.add(administrator);
	}
	public Administrator read(int id) throws SQLException {
		return administrators.stream().filter((administrator) -> administrator.getId() == id).findFirst().get();
	}
	public void update(Administrator administrator) throws SQLException {
		String select = "SELECT base_entity_id FROM person WHERE person_id=" + administrator.getId();
		String create = "UPDATE person SET firstname = :firstname,"
				+ "lastname = :lastname, username = :username, email = :email, "
				+ "password = :password, group = :group WHERE person_id=" + administrator.getId();
		baseEntityDao.update(administrator, jdbcTemplate.queryForObject(select, Integer.class));
		SqlParameterSource source = new BeanPropertySqlParameterSource(administrator);
		parameterJdbcTemplate.update(create, source);
	}
	public void delete(int id) {
		String delete = "DELETE FROM person WHERE person_id=" + id;
		String select = "SELECT base_entity_id FROM person WHERE person_id=" + id;
		baseEntityDao.delete(jdbcTemplate.queryForObject(select, Integer.class));
		jdbcTemplate.update(delete);
	}
	private void getAdministartors() throws SQLException {
		String getAdministrators = "SELECT * FROM base_entity, person WHERE "
				+ "person.base_entity_id = base_entity.base_entity_id AND "
				+ "person.group='Administrator' ORDER BY person.person_id";
		List<Administrator> administrators = parameterJdbcTemplate.query
				(getAdministrators, new AdministratorMapper());
		for(Administrator administrator : administrators) {
			for(Administrator administrator2 : administrators) {
				if(administrator2.getId() == administrator.getCreatedBy().getId()) {
					administrator.setCreatedBy(administrator2);
				}
				if(administrator2.getId() ==  administrator.getModifiedBy().getId()) {
					administrator.setModifiedBy(administrator2);
				}
			}
			if(administrator.getCreatedBy().getEmail().equals("test@mail.com")) {
				administrator.setCreatedBy(null);
			}
			if(administrator.getModifiedBy().getEmail().equals("test@mail.com")) {
				administrator.setModifiedBy(null);
			}
		}
		this.administrators = administrators;
	}
}
