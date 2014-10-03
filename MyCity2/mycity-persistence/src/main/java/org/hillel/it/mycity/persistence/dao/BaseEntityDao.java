package org.hillel.it.mycity.persistence.dao;

import org.hillel.it.mycity.helper.SqlHelper;
import org.hillel.it.mycity.model.entity.BaseEntity;
import org.hillel.it.mycity.persistence.rowmapper.BaseEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

class BaseEntityDao implements AdditionalGenericDao<BaseEntity>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private int lastId;
	public void create(BaseEntity baseEntity) {
		String create = "INSERT INTO base_entity (created_date,created_by) VALUES(?,?)";
		jdbcTemplate.update(create, baseEntity.getCreatedDate(), baseEntity.getCreatedBy().getId());
		lastId = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
	}
	public BaseEntity read(BaseEntity baseEntity, int id) {
		String read = "SELECT * FROM base_entity WHERE base_entity_id=" + id;
		return jdbcTemplate.queryForObject(read, new BaseEntityMapper(baseEntity));
	}
	public void update(BaseEntity baseEntity, int id) {
		String update = "UPDATE base_entity SET modified_date = :modified_date, "
				+ "modified_by = :modified_by WHERE base_entity_id = " + id;
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("modified_date", SqlHelper.getSqlDate(baseEntity.getModifiedDate()));
		source.addValue("modified_by", baseEntity.getModifiedBy().getId());
		parameterJdbcTemplate.update(update, source);
	}
	public void delete(int id) {
		String delete = "DELETE FROM base_entity WHERE base_entity_id=" + id;
		jdbcTemplate.update(delete);
	}
	protected int getLastId() {
		return lastId;
	}
}
