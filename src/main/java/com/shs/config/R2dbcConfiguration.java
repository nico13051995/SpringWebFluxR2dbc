package com.shs.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.util.StringUtils;

import io.r2dbc.postgresql.PostgresqlConnection;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories(basePackages="com.shs.repo")
@EnableConfigurationProperties({ FlywayProperties.class })
class R2dbcConfiguration  extends AbstractR2dbcConfiguration {
	
	@Autowired
	private FlywayProperties properties;
	
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
	
	@Bean
	public PostgresqlConnection postgresqlConnection() {
		return connectionFactory().create().block();
	}
	
    @Bean(initMethod = "migrate")
    public Flyway flyway() {
    	FluentConfiguration configuration = new FluentConfiguration();
    	PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
    	configuration.dataSource(properties.getUrl(), properties.getUser(), properties.getPassword());
		map.from(this.properties.getEncoding()).to(configuration::encoding);
		map.from(this.properties.getConnectRetries())
				.to(configuration::connectRetries);
		map.from(this.properties.getSchemas()).as(StringUtils::toStringArray)
				.to(configuration::schemas);
		map.from(this.properties.getTable()).to(configuration::table);
		map.from(this.properties.getBaselineDescription())
				.to(configuration::baselineDescription);
		map.from(this.properties.getBaselineVersion())
				.to(configuration::baselineVersion);
		map.from(this.properties.getInstalledBy()).to(configuration::installedBy);
		map.from(this.properties.getPlaceholders()).to(configuration::placeholders);
		map.from(this.properties.getPlaceholderPrefix())
				.to(configuration::placeholderPrefix);
		map.from(this.properties.getPlaceholderSuffix())
				.to(configuration::placeholderSuffix);
		map.from(this.properties.isPlaceholderReplacement())
				.to(configuration::placeholderReplacement);
		map.from(this.properties.getSqlMigrationPrefix())
				.to(configuration::sqlMigrationPrefix);
		map.from(this.properties.getSqlMigrationSuffixes())
				.as(StringUtils::toStringArray)
				.to(configuration::sqlMigrationSuffixes);
		map.from(this.properties.getSqlMigrationSeparator())
				.to(configuration::sqlMigrationSeparator);
		map.from(this.properties.getRepeatableSqlMigrationPrefix())
				.to(configuration::repeatableSqlMigrationPrefix);
		map.from(this.properties.getTarget()).to(configuration::target);
		map.from(this.properties.isBaselineOnMigrate())
				.to(configuration::baselineOnMigrate);
		map.from(this.properties.isCleanDisabled()).to(configuration::cleanDisabled);
		map.from(this.properties.isCleanOnValidationError())
				.to(configuration::cleanOnValidationError);
		map.from(this.properties.isGroup()).to(configuration::group);
		map.from(this.properties.isIgnoreMissingMigrations())
				.to(configuration::ignoreMissingMigrations);
		map.from(this.properties.isIgnoreIgnoredMigrations())
				.to(configuration::ignoreIgnoredMigrations);
		map.from(this.properties.isIgnorePendingMigrations())
				.to(configuration::ignorePendingMigrations);
		map.from(this.properties.isIgnoreFutureMigrations())
				.to(configuration::ignoreFutureMigrations);
		map.from(this.properties.isMixed()).to(configuration::mixed);
		map.from(this.properties.isOutOfOrder()).to(configuration::outOfOrder);
		map.from(this.properties.isSkipDefaultCallbacks())
				.to(configuration::skipDefaultCallbacks);
		map.from(this.properties.isSkipDefaultResolvers())
				.to(configuration::skipDefaultResolvers);
		map.from(this.properties.isValidateOnMigrate())
				.to(configuration::validateOnMigrate);
        Flyway flyway = new Flyway(configuration);
        return flyway;
    }

    @Bean
    public FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, null);
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