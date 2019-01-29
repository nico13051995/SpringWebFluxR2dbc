package com.shs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories(basePackages="com.shs.repo")
class R2dbcConfiguration  extends AbstractR2dbcConfiguration {
	
	@Bean
	@Override
	public PostgresqlConnectionFactory connectionFactory() {
		return new PostgresqlConnectionFactory(
		        PostgresqlConnectionConfiguration
		            .builder()
		            .database("test")
		            .username("postgres")
		            .password("admin")
		            .host("127.0.0.1")
		            .port(5432)
		            .build());
	}
/*
	  @Bean
	  public DatabaseClient databaseClient() {
	    return DatabaseClient.builder()
	                         .connectionFactory(connectionFactory())
	                         .build();
	  }

	  @Bean
	  public MappingContext mappingContext() {
	    final RelationalMappingContext relationalMappingContext = new RelationalMappingContext();
	    relationalMappingContext.afterPropertiesSet();
	    return relationalMappingContext;
	  }

	  @Bean
	  public R2dbcRepositoryFactory repositoryFactory() {
	    return new R2dbcRepositoryFactory(databaseClient(), mappingContext(), null);
	  }
	  */
/*
	  @Bean
	  public CoffeeRepository reactiveCoffeeRepository() {
	    return repositoryFactory().getRepository(CoffeeRepository.class);
	  }*/
	  
}