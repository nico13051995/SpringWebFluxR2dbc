package com.shs.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.codegen.GenerationTool;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.postgresql.jdbc4.Jdbc4Connection;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;

@org.springframework.context.annotation.Configuration
public class JooqConfig {
 //NOT NEEDED, CONFIGURATION IN APPLICATION PROPERTIES
	@Bean
	DSLContext create() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test","postgres","admin");
            return DSL.using(conn,SQLDialect.POSTGRES_9_3);
        }
        catch(Exception e) {
            System.out.println("error occured  !!!!");
            e.printStackTrace();
            //return null;
        }

		return null;//SQLDialect.POSTGRES_9_5
	}

	/*public void test(){
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

		DSLContext create = DSL.using();

		try {
			GenerationTool.generate(configuration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
