package com.shs.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.codegen.GenerationTool;
import org.jooq.impl.DSL;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class JooqConfig {

	@Bean
	DSLContext create() {
		return DSL.using(SQLDialect.POSTGRES_9_5);	
	}

	public void test(){
		Configuration configuration = new Configuration()
				.withJdbc(new Jdbc()
						.withDriver("org.postgresql.Driver")
						.withUrl("jdbc:postgresql:postgres")
						.withUser("postgres")
						.withPassword("admin"))
				.withGenerator(new Generator()
						.withDatabase(new Database()
								.withName("org.jooq.util.postgres.PostgresDatabase")
								.withIncludes(".*")
								.withExcludes("")
								.withInputSchema("public"))
						.withTarget(new Target()
								.withPackageName("org.jooq.util.maven.example")
								.withDirectory("target/generated-sources/jooq")));

		DSLContext create = DSL.using(SQLDialect.POSTGRES_9_5);

		try {
			GenerationTool.generate(configuration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
