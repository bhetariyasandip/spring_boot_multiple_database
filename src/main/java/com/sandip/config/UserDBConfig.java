package com.sandip.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "userEntityManager", 
		transactionManagerRef = "userTransactionManager", 
		basePackages = "com.sandip.user.repository"
)
@PropertySources({ @PropertySource("classpath:application.properties") })
public class UserDBConfig {
	
	@Autowired
    private Environment env; // Contains Properties Load by @PropertySources

	@Primary
	@Bean
	public DataSource mysqlUserDataSource() {
		
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSourceBuilder.url(env.getProperty("spring.user.datasource.url"));
        dataSourceBuilder.username(env.getProperty("spring.user.datasource.username"));
        dataSourceBuilder.password(env.getProperty("spring.user.datasource.password"));
        return dataSourceBuilder.build();
		
	}

	@Primary
	@Bean(name = "userEntityManager")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
					.dataSource(mysqlUserDataSource())
					.properties(hibernateProperties())
					.packages("com.sandip.user.entity")
					.persistenceUnit("userPU")
					.build();
	}

	@Primary
	@Bean(name = "userTransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(@Qualifier("userEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Map<String, Object> hibernateProperties() {

		Resource resource = new ClassPathResource("hibernate.properties");
		
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			
			return properties.entrySet().stream()
											.collect(Collectors.toMap(
														e -> e.getKey().toString(),
														e -> e.getValue())
													);
		} catch (IOException e) {
			return new HashMap<String, Object>();
		}
	}
}
