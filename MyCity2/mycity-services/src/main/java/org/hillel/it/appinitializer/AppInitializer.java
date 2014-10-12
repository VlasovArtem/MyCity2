package org.hillel.it.appinitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.hillel.it.mycity.persistence.AppConfig;
import org.hillel.it.mycity.service.ServiceMyCity;
import org.hillel.it.mycity.service.impl.ServiceImpl;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Order(value=Ordered.HIGHEST_PRECEDENCE)
public class AppInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("contextConfigLocation", "");
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		servletContext.addListener(new ContextLoaderListener(context));
	}

}
