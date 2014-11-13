package org.hillel.it.mycity.resource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.hillel.it.mycity.rest.MyApplication;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestResourceTest {
	private static UndertowJaxrsServer server;
	
	@BeforeClass
	public static void init() {
		server = new UndertowJaxrsServer().start();
	}
	
	@AfterClass
	public static void stop() {
		server.stop();
	}
	
	@Test
	public void getId() {
		server.deploy(MyApplication.class);
		Client client = ClientBuilder.newClient();
		String response = client.target(TestPortProvider.generateURL("/hello")).request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(response);
	}
}
