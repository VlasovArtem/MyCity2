package org.hillel.it.rest;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("")
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig() {
		packages("org.hillel.it");
	}
}
