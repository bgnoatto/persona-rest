package org.bgnoatto.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bgnoatto.rest.dao.PersonaDAO;
import org.bgnoatto.rest.db.HibernateUtil;
import org.bgnoatto.rest.domain.Persona;
import org.hibernate.Criteria;
import org.hibernate.Session;

@Path("/personas")
public class PersonaRestService extends PersonaDAO {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> findAll() {
		testHibernate();

		List<Persona> personas = getAllPersonas();
		return personas;
		// return Response.ok(personas, MediaType.APPLICATION_JSON).status(200).build();
	}

	private void testHibernate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// Add new Employee object
		Persona persona = new Persona();
		persona.setDni(31);
		persona.setNombre("HOLA");
		persona.setApellido("CHAU");
		persona.setEdad(34);
		// emp.setEmployeeId(1);
		// emp.setEmail("demo-user@mail.com");
		// emp.setFirstName("demo");
		// emp.setLastName("user");
		session.save(persona);
		session.getTransaction().commit();
		
		Criteria crit = session.createCriteria(Persona.class);
		List<Persona> results = (List<Persona>)crit.list();
		
		
		HibernateUtil.shutdown();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{ID_persona}")
	public Response findById(@PathParam(value = "ID_persona") Integer idpersona) {
		Persona persona = new Persona();
		persona.setDni(idpersona);
		return Response.ok(persona, MediaType.APPLICATION_JSON).status(200).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{ID_persona}")
	public Response crearActa(@PathParam(value = "ID_persona") Integer idpersona, Persona persona) {
		persona.setDni(idpersona);

		return Response.ok(persona, MediaType.APPLICATION_JSON).status(201).build();
	}

	@PUT
	@Path("/{ID_persona}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("ID_persona") long idpersona, Persona persona) {
		// update persona
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{ID_persona}")
	public Response delete(@PathParam("ID_persona") long idpersona) {
		// Delete persona
		return Response.status(Status.NO_CONTENT).entity("Persona borrada!!").build();
	}
}
