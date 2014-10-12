package org.hillel.it.mycity.helper;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.io.IOUtils;

public class SqlHelper {
	public static Date getSqlDate(java.util.Date date) {
		Date sqlDate = new Date(date.getTime());
		return sqlDate;
	}
	public static int getLastInsertID(Connection conn, String statment) throws SQLException {
		int lastInsertedId = -1;
		PreparedStatement ps = conn.prepareStatement(statment,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();

		ResultSet rskey = ps.getGeneratedKeys();
		if (rskey != null && rskey.next()) {
			lastInsertedId = rskey.getInt(1);
		}
		return lastInsertedId;
	}
	public static Time getSqlTime(LocalTime time) {
		Time sqlTime = new Time(time.getHour(),time.getMinute(),0);
		return sqlTime;
	}
	public static DataSource getDataSource() {
		Properties properties = new Properties();
		InputStream is = SqlHelper.class.getClassLoader()
				.getResourceAsStream("application.properties");
		DataSource dataSource = null;
		try {
			properties.load(is);
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(is);
		}
		return dataSource;
	}
}
