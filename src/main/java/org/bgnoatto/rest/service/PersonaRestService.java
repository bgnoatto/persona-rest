package org.bgnoatto.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bgnoatto.rest.dao.PersonaDAO;
import org.bgnoatto.rest.domain.Persona;

@Path("/personas")
public class PersonaRestService extends PersonaDAO {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Persona> personas = getAllPersonas();
		return Response.ok(personas, MediaType.APPLICATION_JSON).status(200).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{ID_persona}")
	public Response savePersona(@PathParam(value = "ID_persona") Integer idpersona, Persona persona) {
		persona.setDni(idpersona);
		persona = addPersona(persona);
		return Response.ok(persona, MediaType.APPLICATION_JSON).status(200).build();
	}
}
