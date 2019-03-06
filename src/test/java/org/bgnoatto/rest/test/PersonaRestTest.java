package org.bgnoatto.rest.test;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.Application;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.bgnoatto.rest.domain.Persona;
//import org.bgnoatto.rest.service.PersonaRestService;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.test.JerseyTest;
//import org.glassfish.jersey.test.TestProperties;
//import org.junit.Test;
//
//public class PersonaRestTest extends JerseyTest {
//
//	@Override
//	public Application configure() {
//		enable(TestProperties.LOG_TRAFFIC);
//		enable(TestProperties.DUMP_ENTITY);
//		return new ResourceConfig(PersonaRestService.class);
//	}
//
//	@Test
//	public void testPersonasAll() {
//		Response response = target("/personas").request().get();
//		assertEquals("should return status 200", 200, response.getStatus());
//		assertNotNull("Should return user list", response.getEntity().toString());
//		System.out.println(response.getStatus());
//		// System.out.println(response.readEntity(String.class));
//	}
//
//	@Test
//	public void testGetPersonaById() {
//		Response output = target("/personas/31115172").request().get();
//		assertEquals("Should return status 200", 200, output.getStatus());
//		assertNotNull("Should return user object as json", output.getEntity());
//		System.out.println(output.getStatus());
//		// System.out.println(output.readEntity(String.class));
//	}
//
//	@Test
//	public void testCreatePersona() {
//		Persona persona = new Persona("BRUNO", "GNOATTO", 34);
//		Response output = target("/personas/31115172").request().post(Entity.entity(persona, MediaType.APPLICATION_JSON));
//		System.out.println(output.getStatus());
//		assertEquals("Should return status 201", 201, output.getStatus());
//	}
//
//	@Test
//	public void testUpdatePersona() {
//		Persona persona = new Persona(31115172, "BRUNO", "GNOATTO", 35);
//		Response output = target("/personas/31115172").request().put(Entity.entity(persona, MediaType.APPLICATION_JSON));
//		assertEquals("Should return status 204", 204, output.getStatus());
//		System.out.println(output.getStatus());
//	}
//
//	@Test
//	public void testDeletePersona() {
//		Response output = target("/personas/31115172").request().delete();
//		assertEquals("Should return status 204", 204, output.getStatus());
//	}
//
//}
