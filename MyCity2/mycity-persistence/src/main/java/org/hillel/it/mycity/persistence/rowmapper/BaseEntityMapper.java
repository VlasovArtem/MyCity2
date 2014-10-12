package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.BaseEntity;
import org.springframework.jdbc.core.RowMapper;

public class BaseEntityMapper implements RowMapper<BaseEntity>{
	private BaseEntity baseEntity;
	public <T extends BaseEntity> BaseEntityMapper(T t) {
		baseEntity = t;
	}
	@Override
	public BaseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		baseEntity.setCreatedDate(rs.getDate("created_date"));
		baseEntity.setModifiedDate(rs.getDate("modified_date"));
		//baseEntity.setCreatedBy(administratorDao.read(rs.getInt("created_by")));
		//baseEntity.setModifiedBy(administratorDao.read(rs.getInt("modified_by")));
		return baseEntity;
	}

}
