package org.hillel.it.mycity.resource;

import java.util.Set;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.hillel.it.mycity.persistence.AppConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.ImmutableSet;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class} )
public class TestResourceTest extends JerseyTest{

	@Override
	protected Application configure() {
		return new Application(){
			@Override
			public Set<Class<?>> getClasses() {
				return ImmutableSet.<Class<?>>of(TestResource.class);
			}
			
		};
	}

	@Override
	protected TestContainerFactory getTestContainerFactory()
			throws TestContainerException {
		return new JettyTestContainerFactory();
	}

	@Test
	public void getId() {
		WebTarget target = client().target("http://localhost:9998/");
		String string = target.path("hello").request().get(String.class);
		System.out.println(string);
	}
	
	
	
	
}
