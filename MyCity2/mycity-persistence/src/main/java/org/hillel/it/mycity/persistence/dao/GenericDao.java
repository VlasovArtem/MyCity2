package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;

public interface GenericDao<T> {
	public void create(T t) throws SQLException;
	public T read(int id) throws SQLException;
	public void update(T t) throws SQLException;
	public void delete(int id) throws SQLException;
}
