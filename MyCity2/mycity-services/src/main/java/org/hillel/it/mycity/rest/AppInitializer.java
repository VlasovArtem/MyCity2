package org.hillel.it.mycity.rest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.glassfish.jersey.servlet.ServletContainer;
import org.hillel.it.mycity.persistence.AppConfig;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class AppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.addListener(ContextLoaderListener.class);
		servletContext.setInitParameter(ContextLoader.CONTEXT_CLASS_PARAM, AnnotationConfigWebApplicationContext.class.getName());
		servletContext.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, AppConfig.class.getName());
		ServletRegistration.Dynamic servletRegistartion = servletContext.addServlet("app", ServletContainer.class.getName());
		
		servletRegistartion.addMapping("/*");
		servletRegistartion.setLoadOnStartup(1);
		servletRegistartion.setInitParameter("javax.ws.rs.Application", MyApplication.class.getName());
	}
	
}
