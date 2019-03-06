package org.bgnoatto.rest.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitHSqlDBTest {

	@BeforeClass
	public static void init() throws SQLException, ClassNotFoundException, IOException {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");

		// initialize database
		initDatabase();
	}
	

//	@AfterClass
//	public static void destroy() throws SQLException, ClassNotFoundException, IOException {
//		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
//			statement.executeUpdate("DROP TABLE IF EXISTS persona");
//			connection.commit();
//		}
//	}

	/**
	 * Database initialization for testing i.e.
	 * <ul>
	 * <li>Creating Table</li>
	 * <li>Inserting record</li>
	 * </ul>
	 * 
	 * @throws SQLException
	 */
	private static void initDatabase() throws SQLException {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			statement.execute("CREATE TABLE IF NOT EXISTS persona (dni INT NOT NULL, nombre VARCHAR(250),"
					+ "apellido VARCHAR(250), edad INT, PRIMARY KEY (dni))");
			connection.commit();
			statement.executeUpdate("INSERT INTO persona VALUES (31115172,'BRUNO', 'GNOATTO',34)");
			statement.executeUpdate("INSERT INTO persona VALUES (32701794,'FLORENCIA', 'CITTA',32)");
			connection.commit();
		}
	}

	/**
	 * Create a connection
	 * 
	 * @return connection object
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:hsqldb:mem:personas", "SA", "");
	}

	/**
	 * Get total records in table
	 * 
	 * @return total number of records. In case of exception 0 is returned
	 */
	private int getTotalRecords() {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			ResultSet result = statement.executeQuery("SELECT count(*) as total FROM persona");
			if (result.next()) {
				return result.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Test
	public void getTotalRecordsTest() {
		assertThat(2, is(getTotalRecords()));
	}

	@Test
	public void checkNameExistsTest() {
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			ResultSet result = statement.executeQuery("SELECT nombre FROM persona");

			if (result.first()) {
				assertThat("BRUNO", is(result.getString("nombre")));
			}

			if (result.last()) {
				assertThat("FLORENCIA", is(result.getString("nombre")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
