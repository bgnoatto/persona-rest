package hsqldb.test;

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
	

	@AfterClass
	public static void destroy() throws SQLException, ClassNotFoundException, IOException {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("DROP TABLE employee");
			connection.commit();
		}
	}

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
			statement.execute("CREATE TABLE employee (id INT NOT NULL, name VARCHAR(50) NOT NULL,"
					+ "email VARCHAR(50) NOT NULL, PRIMARY KEY (id))");
			connection.commit();
			statement.executeUpdate(
					"INSERT INTO employee VALUES (1001,'Vinod Kumar Kashyap', 'vinod@javacodegeeks.com')");
			statement.executeUpdate("INSERT INTO employee VALUES (1002,'Dhwani Kashyap', 'dhwani@javacodegeeks.com')");
			statement.executeUpdate("INSERT INTO employee VALUES (1003,'Asmi Kashyap', 'asmi@javacodegeeks.com')");
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
		return DriverManager.getConnection("jdbc:hsqldb:mem:employees", "vinod", "vinod");
	}

	/**
	 * Get total records in table
	 * 
	 * @return total number of records. In case of exception 0 is returned
	 */
	private int getTotalRecords() {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			ResultSet result = statement.executeQuery("SELECT count(*) as total FROM employee");
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
		assertThat(3, is(getTotalRecords()));
	}

	@Test
	public void checkNameExistsTest() {
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {

			ResultSet result = statement.executeQuery("SELECT name FROM employee");

			if (result.first()) {
				assertThat("Vinod Kumar Kashyap", is(result.getString("name")));
			}

			if (result.last()) {
				assertThat("Asmi Kashyap", is(result.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
