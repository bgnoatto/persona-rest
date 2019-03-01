package org.bgnoatto.rest;

import java.sql.Connection;
import java.util.ArrayList;
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
	public Response printMessage() {
		List<Persona> personas = getAllPersonas();
		return Response.ok(personas, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{ID_persona}")
	public Response crearActa(@PathParam(value = "ID_persona") Long idpersona, Persona persona) {
		persona.setDni(idpersona);
		try {
			Connection con = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok(persona, MediaType.APPLICATION_JSON).build();
	}

	private List<Persona> getAllPersonas() {
		List<Persona> resultado = new ArrayList<>();
		
		Persona nueva = new Persona((long) 31115172,"Bruno","Gnoatto",34);
		Persona nueva2 = new Persona((long) 32701794,"Florencia","Citta",32);
		
		resultado.add(nueva);
		resultado.add(nueva2);
		
		return resultado;
	}
}
