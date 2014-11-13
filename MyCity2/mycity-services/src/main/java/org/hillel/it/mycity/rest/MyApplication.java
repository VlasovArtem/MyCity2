package org.hillel.it.mycity.rest;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.hillel.it.mycity.resource.TestResource;

import com.google.common.collect.ImmutableSet;

@ApplicationPath("/")
public class MyApplication extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		return ImmutableSet.<Class<?>>of(TestResource.class);
	}
	
}
