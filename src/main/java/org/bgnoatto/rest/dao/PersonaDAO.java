package org.bgnoatto.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class PersonaDAO {

	static private PersonaDAO personaDAO = null;

	static private Connection conn = null;

	static private String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
	static private String URL = "jdbc:hsqldb:mem:.";
	static private String USER = "SA";
	static private String PASSWD = "";

	public PersonaDAO() {
		super();
	}

	static public PersonaDAO getInstance() throws Exception {
		if (personaDAO == null) {
			personaDAO = new PersonaDAO();

			conn = personaDAO.getConnection();
		}

		return personaDAO;
	}

	protected Connection getConnection() throws Exception {

		if (conn != null && !conn.isClosed()) {
			return conn;
		}

		try {
			// Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			// Creating the connection with HSQLDB
			conn = DriverManager.getConnection(URL, USER,PASSWD);
			if (conn != null) {
				System.out.println("Connection created successfully");

			} else {
				System.out.println("Problem with creating connection");
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return conn;
	}
}
