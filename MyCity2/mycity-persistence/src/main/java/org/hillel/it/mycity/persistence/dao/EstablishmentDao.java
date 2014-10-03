package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.persistence.rowmapper.EstablishmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

class EstablishmentDao implements AdditionalGenericDao<Establishment>{
	private BaseEntityDao baseEntityDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private int lastId;
	public EstablishmentDao() {
		baseEntityDao = new BaseEntityDao();
	}
	public void create(Establishment establishment) throws SQLException {
		baseEntityDao.create(establishment);
		String create = "INSERT INTO establishment (base_entity_id,name,address,telephone,"
				+ "description) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(create, baseEntityDao.getLastId(), establishment.getName(), 
				establishment.getAddress(), establishment.getPhone(), establishment.getDescription());
		lastId = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
	}
	public Establishment read(Establishment establishment, int id) {
		String read = "SELECT * FROM establishment WHERE establishment_id=" + id;
		String select = "SELECT base_entity_id FROM establishment WHERE establishment_id=" + id;
		Establishment newEstablishment = jdbcTemplate.queryForObject(read, new EstablishmentMapper(establishment));
		baseEntityDao.read(establishment, jdbcTemplate.queryForObject(select, Integer.class));
		return newEstablishment;
	}
	public void update(Establishment establishment, int id) throws DataAccessException, SQLException {
		String update = "UPDATE establishment SET name = :name, address = :address, telephone = :telephone, "
				+ "description = :description WHERE establishment_id = " + id;
		String select = "SELECT base_entity_id FROM establishment WHERE "
				+ "establishment_id = " + id;
		baseEntityDao.update(establishment, jdbcTemplate.queryForObject(select, Integer.class));
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("name", establishment.getName());
		source.addValue("address", establishment.getAddress());
		source.addValue("telephone", establishment.getPhone());
		source.addValue("description", establishment.getDescription());
		parameterJdbcTemplate.update(update, source);
	}
	public void delete(int id) {
		String select = "SELECT base_entity_id FROM establishment WHERE "
				+ "establishment_id = " + id;
		String delete = "DELETE * FROM establishment WHERE establishment_id=" + id;
		baseEntityDao.delete(jdbcTemplate.queryForObject(select, Integer.class));
		jdbcTemplate.update(delete);
	}
	protected int getLastId() {
		return lastId;
	}
}
