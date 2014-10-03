package org.hillel.it.mycity.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CommentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate parameterJdbcTemplate;
	private BaseEntityDao baseEntityDao;
	public CommentDao() {
		baseEntityDao = new BaseEntityDao();
	}
}
