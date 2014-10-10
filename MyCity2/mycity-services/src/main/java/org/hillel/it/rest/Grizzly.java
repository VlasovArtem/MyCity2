package org.hillel.it.rest;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Grizzly {
	public static final String BASE_URI = "http://localhost:8080/webapp/";
	public static HttpServer startServer() {
		final ResourceConfig rc = new ResourceConfig().packages("org.hillel.it");
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI));
	}
	
	public static void main(String[] args) throws IOException {
		final HttpServer server = startServer();
		System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
	}
}
