package org.hillel.it.mycity.persistence.repository.inmemory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

import org.hillel.it.mycity.helper.SqlHelper;
import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.persistence.repository.UserRepository;

@Deprecated
public class DataBaseUserRepository extends FileUserRepository implements UserRepository{

	private static final long serialVersionUID = 5676602279246927895L;
	private DataSource dataSource;
	private int baseEntityId;
	
	public DataBaseUserRepository() {
		dataSource = SqlHelper.getDataSource();
	}
	
	@Override
	public boolean checkUserInDB(String email, String password) {
		try (Connection connection = dataSource.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM Person WHERE email='"
								+ email + "' AND password='" + password + "';");
				if (!resultSet.next()) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public Moderator getModerator(int id) {
		return null;
	}
	
	@Override
	public Administrator getAdministrator(int id) {
		return null;
	}
	
	protected void addUser(Person person) throws SQLException {
		String baseEntitySQL = "INSERT INTO base_entity (created_by, created_date) VALUES(?,?);";
		String personSQL = "INSERT INTO person (base_entity_id,firstname,lastname,username,email,password,group) VALUES(?,?,?,?,?,?,?)";
		try(Connection connection = dataSource.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(baseEntitySQL)) {
				ps.setInt(1, 2);
				ps.setDate(2, SqlHelper.getSqlDate(person.getCreatedDate()));
				baseEntityId = SqlHelper.getLastInsertID(connection, baseEntitySQL);
			}
			try(PreparedStatement ps = connection.prepareStatement(personSQL)) {
				ps.setInt(1, baseEntityId);
				ps.setString(2, person.getFirstName());
				ps.setString(3, person.getLastName());
				ps.setString(4, person.getUsername());
				ps.setString(5, person.getEmail());
				ps.setString(6, person.getPassword());
				ps.setString(7, person.getGroup().name());
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	@Override
	protected void flushDB(Person person) throws SQLException {
		addUser(person);
	}
}
