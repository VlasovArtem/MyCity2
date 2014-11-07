package org.hillel.it.mycity.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.hillel.it.mycity.resource.TestResource;

@ApplicationPath("/")
public class JerseyConfig extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(TestResource.class);
		return s;
	}
	
}
