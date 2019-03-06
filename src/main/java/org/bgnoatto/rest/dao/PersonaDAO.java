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
		return resultado;
	}
	
	public Persona addPersona(Persona persona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(persona);
		session.getTransaction().commit();
		return persona;
	}
}
