package org.hillel.it.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.hillel.it.resource.TestResource;

@ApplicationPath("/jersey/")
public class JerseyConfig extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(TestResource.class);
		return s;
	}
	
}
