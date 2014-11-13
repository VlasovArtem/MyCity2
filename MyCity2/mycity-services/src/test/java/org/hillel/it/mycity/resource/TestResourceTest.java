package org.hillel.it.mycity.resource;

import java.util.Set;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.hillel.it.mycity.persistence.AppConfig;
import org.hillel.it.mycity.rest.AppInitializer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, AppInitializer.class} )
public class TestResourceTest extends JerseyTest{
	
	@Override
	protected Application configure() {
		ResourceConfig rc = new ResourceConfig();
		rc.registerClasses(TestResource.class)
		.property("contextConfig", new AnnotationConfigApplicationContext(AppConfig.class))
		.register(SpringLifecycleListener.class)
		.register(RequestContextFilter.class);
		return rc;
	}

	@Override
	protected TestContainerFactory getTestContainerFactory()
			throws TestContainerException {
		return new JettyTestContainerFactory();
	}

	@Test
	public void getId() {
		WebTarget target = client().target("http://localhost:8080/");
		String string = target.path("hello").request().get(String.class);
		System.out.println(string);
	}

}
