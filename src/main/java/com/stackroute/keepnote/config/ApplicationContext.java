package com.stackroute.keepnote.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.model.User;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "com.stackroute.keepnote")
public class ApplicationContext {

	@Bean
	@Autowired
	public DataSource getDataSource() {

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/noteDb3");
		dataSource.setUsername("root");
		dataSource.setPassword("root123");

		return dataSource;

	}

	@Bean
	@Autowired
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) throws IOException {

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");

		sessionFactoryBean.setAnnotatedClasses(Note.class, User.class, Category.class, Reminder.class);

		sessionFactoryBean.setHibernateProperties(properties);
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean;

	}

	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager transaction = new HibernateTransactionManager();
		transaction.setSessionFactory(sessionFactory);

		return transaction;
	}

}
