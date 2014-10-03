package org.hillel.it.mycity.persistence.dao;

import java.sql.SQLException;

public interface AdditionalGenericDao<T> {
	public void create(T t) throws SQLException;
	public T read(T t, int id);
	public void update(T t, int id) throws SQLException;
	public void delete(int id);
}
