package org.hillel.it.mycity.persistence;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan("org.hillel.it")
public class AppConfig {
	@Autowired
	private Environment env;
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setPackagesToScan("org.hillel.it");
		bean.setDataSource(dataSource);
		Properties properties = new Properties();
		properties.put("hinernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.cache.use_second_level_cache", env.getProperty("second.level.cache"));
		properties.put("hibernate.cache.provider_class", env.getProperty("cache.provider.class"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hbm2ddl.auto"));
		properties.put("hibernate.show_sql", env.getProperty("show_sql"));
		properties.put("hibernate.chache.region.factory_class", env.getProperty("cache.region.class"));
		bean.setHibernateProperties(properties);
		bean.setEntityInterceptor(new GlobalInterceptor());
		return bean;
	}
	
}
