package org.bgnoatto.rest.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bgnoatto.rest.db.HibernateUtil;
import org.bgnoatto.rest.domain.Persona;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class PersonaDAO {
	
	public List<Persona> getAllPersonas(){
		List<Persona> resultado = new ArrayList<Persona>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Persona.class);
		if(criteria.list()!=null || !criteria.list().isEmpty()) {
			List<Persona> results = criteria.list();
			Iterator<Persona> it = results.iterator();
			
			while(it.hasNext()) {
				resultado.add(it.next());
			}
		}
		
		
		session.getTransaction().commit();
//		HibernateUtil.shutdown();
//		try {
//			conn = getDBConnection();
//			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//					ResultSet.CONCUR_READ_ONLY);
//			ResultSet result = statement.executeQuery("SELECT dni,nombre,apellido,edad FROM persona");
//			System.out.println(result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return resultado;
	}
	
	public Persona addPersona(Persona persona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(persona);
		session.getTransaction().commit();
//		HibernateUtil.shutdown();
		return persona;
	}

//	static private PersonaDAO personaDAO = null;
//
//	static private Connection conn = null;
//
//	static private String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
//	static private String URL = "jdbc:hsqldb:mem:.";
//	static private String USER = "SA";
//	static private String PASSWD = "";

//	public PersonaDAO() {
//		super();
//	}

//	static public PersonaDAO getInstance() throws Exception {
//		if (personaDAO == null) {
//			personaDAO = new PersonaDAO();
//
//			conn = personaDAO.getDBConnection();
//		}
//
//		return personaDAO;
//	}

//	protected Connection getDBConnection() {
//
//		try {
//			if (conn != null && !conn.isClosed()) {
//				return conn;
//			}
//			
//			// Registering the HSQLDB JDBC driver
//			Class.forName("org.hsqldb.jdbc.JDBCDriver");
//			
//			conn = initDatabase();
//
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		
//		return conn;
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
//	private static Connection initDatabase() {
//		try {
//			conn = getConnection(); Statement statement = conn.createStatement();
//			statement.execute("CREATE TABLE IF NOT EXISTS persona (dni INT NOT NULL, nombre VARCHAR(250),"
//					+ "apellido VARCHAR(250), edad INT, PRIMARY KEY (dni))");
//			conn.commit();
//		}catch (SQLException e) {
//			conn = null;
//			e.printStackTrace();
//			
//		}
//		return conn;
//	}
	
	/**
	 * Create a connection
	 * 
	 * @return connection object
	 * @throws SQLException
	 */
//	private static Connection getConnection() throws SQLException {
//		return DriverManager.getConnection("jdbc:hsqldb:mem:personas", "SA", "");
//	}
}
