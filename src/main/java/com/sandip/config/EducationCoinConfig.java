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
		entityManagerFactoryRef = "educationEntityManager", 
		transactionManagerRef = "educationTransactionManager", 
		basePackages = "com.sandip.education.repository"
)
@PropertySources({ @PropertySource("classpath:application.properties") })
public class EducationCoinConfig {
	
	@Autowired
    private Environment env; // Contains Properties Load by @PropertySources

	@Bean
	public DataSource mysqlEducationDataSource() {
		
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSourceBuilder.url(env.getProperty("spring.education.datasource.url"));
        dataSourceBuilder.username(env.getProperty("spring.education.datasource.username"));
        dataSourceBuilder.password(env.getProperty("spring.education.datasource.password"));
        return dataSourceBuilder.build();
		
	}

	@Bean(name = "educationEntityManager")
	public LocalContainerEntityManagerFactoryBean mysqlEducationEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
					.dataSource(mysqlEducationDataSource())
					.properties(hibernateProperties())
					.packages("com.sandip.education.entity")
					.persistenceUnit("educationPU")
					.build();
	}

	@Bean(name = "educationTransactionManager")
	public PlatformTransactionManager mysqlEducationTransactionManager(@Qualifier("educationEntityManager") EntityManagerFactory entityManagerFactory) {
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
