package org.hillel.it.mycity.persistence.repository.inmemory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hillel.it.mycity.connectionpool.ConnectionPool;
import org.hillel.it.mycity.connectionpool.ReuseableConnectionPool;
import org.hillel.it.mycity.helper.SqlHelper;
import org.hillel.it.mycity.info.Configuration;
import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.UserRepository;

public class DataBaseUserRepository extends FileUserRepository implements UserRepository{

	private static final long serialVersionUID = 5676602279246927895L;
	private ConnectionPool cp;
	private int baseEntityId;
	
	public DataBaseUserRepository() {
		Configuration configuration = Configuration.getInstance();
		cp = new ReuseableConnectionPool(configuration.getUrl(), configuration.getMaxConnCount());
	}
	
	@Override
	public boolean checkUserInDB(String email, String password) {
		Connection connection = cp.getConnection();
		try(Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Person WHERE email='" + email + "' AND password='" + password + "';");
			if(!resultSet.next()) {
				return false;
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
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(baseEntitySQL)) {
				ps.setInt(1, 2);
				ps.setDate(2, SqlHelper.getSQLDate(person.getCreatedDate()));
				baseEntityId = SqlHelper.getLastInsertedId(ps);
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
