package org.hillel.it.mycity.persistence.repository.inmemory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.hillel.it.mycity.connectionpool.ConnectionPool;
import org.hillel.it.mycity.connectionpool.ReuseableConnectionPool;
import org.hillel.it.mycity.helper.SqlHelper;
import org.hillel.it.mycity.info.Configuration;
import org.hillel.it.mycity.model.entity.BaseEntity;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;

public class DataBaseEstablishmentRepository extends InMemoryEstablishmentRepository implements EstablishmentRepository{
	private ConnectionPool cp;
	private int baseEntityId = 0;
	private int establishmentId = 0;
	
	public DataBaseEstablishmentRepository() {
		Configuration configuration = Configuration.getInstance();
		cp = new ReuseableConnectionPool(configuration.getUrl(), configuration.getMaxConnCount());
	}
	
	private <T extends BaseEntity>void insertBaseEntityData(T t) throws SQLException {
		String insertCreateBaseEntity = "INSERT INTO base_entity (created_date,created_by) VALUES(?,?)";
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(insertCreateBaseEntity)) {
				ps.setDate(1, SqlHelper.getSQLDate(t.getCreatedDate()));
				ps.setInt(2, 2);
				baseEntityId = SqlHelper.getLastInsertedId(ps);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private <T extends Establishment>void insertEstablishmentData(T t) throws SQLException {
		String insertEstablishmentSQL = "INSERT INTO establishment (base_entity_id, name, address, telephone, description) VALUES(?,?,?,?,?) ";
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(insertEstablishmentSQL)) {
				ps.setInt(1, baseEntityId);
				ps.setString(2, t.getName());
				ps.setString(3, t.getAddress());
				ps.setString(4, t.getPhone());
				ps.setString(5, t.getDescription());
				establishmentId = SqlHelper.getLastInsertedId(ps);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private void insertCinemaData(Cinema cinema) throws SQLException {
		String insertCinemaSQL = "INSERT INTO cinema (establishment_id, number_of_halls, number_of_seats_in_halls, cinema_technology) VALUES (?,?,?,?)";
		int cinemaId = 0;
		Map<String, Integer> cinemasMap = new HashMap<>();
		insertEstablishmentData(cinema);
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(insertCinemaSQL)) {
				ps.setInt(1, establishmentId);
				ps.setInt(2, cinema.getNumberOfHalls());
				ps.setInt(3, cinema.getNumberOfSeatsInHall());
				ps.setString(4, cinema.getCinemaTechnology());
				cinema.setId(SqlHelper.getLastInsertedId(ps));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private void insertRestaurantData(Restaurant restaurant) throws SQLException {
		String insertRestaurantSQL = "INSERT INTO restaurant (establishment_id, time_open, time_close, avarage_check) VALUES(?,?,?,?)";
		Map<String, Integer> restaurantsMap = new HashMap<>();
		insertEstablishmentData(restaurant);
		int restaurantId = 0;
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(insertRestaurantSQL)) {
				 ps.setInt(1, establishmentId);
				 ps.setString(2, restaurant.getTimeOpen());
				 ps.setString(3, restaurant.getTimeClose());
				 ps.setInt(4, restaurant.getAverageCheck());
				 restaurant.setId(SqlHelper.getLastInsertedId(ps));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private void insertNightClubData(NightClub nightClub) throws SQLException {
		String insertNightClubSQL = "INSERT INTO night_club (establishment_id, time_open, time_close, avarage_check) VALUES(?,?,?,?)";
		Map<String, Integer> nightClubsMap = new HashMap<>();
		int nightClubId = 0;
		insertEstablishmentData(nightClub);
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(insertNightClubSQL)) {
				ps.setInt(1, establishmentId);
				 ps.setString(2, nightClub.getTimeOpen());
				 ps.setString(3, nightClub.getTimeClose());
				 ps.setInt(4, nightClub.getAverageCheck());
				 nightClub.setId(SqlHelper.getLastInsertedId(ps));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private <T extends BaseEntity>void updateBaseEntity(T t) throws SQLException {
		String simpleName = t.getClass().getSimpleName().toLowerCase();
		String updateBaseEntity = "UPDATE base_entity be SET modified_date = ?, modified_by = ? "
				+ "WHERE be.base_entity_id = (SELECT e.base_entity_id FROM establishment e "
				+ "WHERE e.establishment_id = (SELECT c.establishment_id FROM cinema c "
				+ "WHERE cinema_id = ?))";
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(updateBaseEntity)) {
				ps.setDate(1, SqlHelper.getSQLDate(t.getModifiedDate()));
				ps.setInt(2, 2);
				ps.setInt(3, t.getId());
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private <T extends Establishment>void updateEstablishment(T t) throws SQLException {
		String updateEstablishment = "UPDATE establishment e SET name = ?, address = ?, "
				+ "telephone = ?, description = ? "
				+ "WHERE e.establishment_id = (SELECT c.establishment_id FROM cinema c "
				+ "WHERE cinema_id = ?))";
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(updateEstablishment)) {
				ps.setString(1, t.getName());
				ps.setString(2, t.getAddress());
				ps.setString(3, t.getPhone());
				ps.setString(4, t.getDescription());
				ps.setInt(5, t.getId());
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private void updateCinema(Cinema cinema) throws SQLException {
		updateBaseEntity(cinema);
		updateEstablishment(cinema);
		String updateCinema = "UPDATE cinema SET number_of_halls = ?, "
				+ "number_of_seats_in_halls = ?, cinema_technology = ? WHERE cinema_id = ?";
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(updateCinema)) {
					ps.setInt(1, cinema.getNumberOfHalls());
					ps.setInt(2, cinema.getNumberOfSeatsInHall());
					ps.setString(3, cinema.getCinemaTechnology());
					ps.setInt(4, cinema.getId());
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} 
	}
	
	private void updateAllCinemas() {
		for(Cinema cinema : cinemas) {
			try {
				updateCinema(cinema);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
					
	}
	
	private void updateRestaurant(Restaurant restaurant) throws SQLException {
		updateBaseEntity(restaurant);
		updateEstablishment(restaurant);
		String updateRestaurant = "UPDATE restaurant SET timeOpen = ?, timeClose = ?, "
				+ "avarage_check = ? WHERE restaurant_id = ?";
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(updateRestaurant)) {
				ps.setString(1, restaurant.getTimeOpen());
				ps.setString(2, restaurant.getTimeClose());
				ps.setInt(3, restaurant.getAverageCheck());
				ps.setInt(4, restaurant.getId());
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} 
	}
	private void updateAllRestaurants() throws SQLException {
		try{
			for(Restaurant restaurant : restaurants) {
				updateRestaurant(restaurant);	
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} 
	}
	
	
	private void updateNightClub(NightClub nightClub) throws SQLException {
		updateBaseEntity(nightClub);
		updateEstablishment(nightClub);
		String updateNightClub = "UPDATE night_club SET timeOpen = ?, timeClose = ?, "
				+ "avarage_check = ? WHERE night_club_id = ?";
		try(Connection connection = cp.getConnection()) {
			try(PreparedStatement ps = connection.prepareStatement(updateNightClub)) {
					ps.setString(1, nightClub.getTimeOpen());
					ps.setString(2, nightClub.getTimeClose());
					ps.setInt(3, nightClub.getAverageCheck());
					ps.setInt(4, nightClub.getId());
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} 
	}
	private void updateAllNightClubs() throws SQLException {
		try {
			for(NightClub nightClub : nightClubs) {
				updateNightClub(nightClub);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} 
	}
	
	@Override
	protected void flushCinema(Cinema cinema) throws SQLException {
		insertCinemaData(cinema);
	}
	
	@Override
	protected void flushRestaurant(Restaurant restaurant) throws SQLException {
		insertRestaurantData(restaurant);
	}
	
	@Override
	protected void flushNightClub(NightClub nightClub) throws SQLException {
		insertNightClubData(nightClub);
	}
	
	@Override
	public void updateDataBase() throws SQLException {
		updateAllCinemas();
		updateAllNightClubs();
		updateAllRestaurants();
	}
	
}
