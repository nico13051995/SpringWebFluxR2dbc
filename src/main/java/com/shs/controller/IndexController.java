package com.shs.controller;

import java.util.logging.Level;

import javax.annotation.PostConstruct;

import com.shs.codegen.maven.jooq.tables.BcUser;
import org.jooq.DSLContext;
import org.jooq.conf.ParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.web.bind.annotation.RestController;

import com.shs.model.ShsUser;
import com.shs.repo.UserRepository;

import io.r2dbc.postgresql.PostgresqlBatch;
import io.r2dbc.postgresql.PostgresqlConnection;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DatabaseClient databaseClient;
	
	@Autowired
	PostgresqlConnection postgresqlConnection;
	
	@Autowired
	DSLContext pJooq;

	@PostConstruct
	public Mono<ShsUser> create(){
		/*pJooq.select(field("BOOK.TITLE"), field("AUTHOR.FIRST_NAME"), field("AUTHOR.LAST_NAME"))
        .from(table("BOOK"))
        .join(table("AUTHOR"))
        .on(field("BOOK.AUTHOR_ID").eq(field("AUTHOR.ID")))
        .where(field("BOOK.PUBLISHED_IN").eq(1948))
        .getSQL();*/

		
		/*userRepository.findAll().doOnError((err)->{
			System.out.println(err);
		}).subscribe((u)->{
			System.out.println(u.toString());
		});*/
		
		long startTime = System.nanoTime();
		PostgresqlBatch batch = postgresqlConnection.createBatch();
		
		String query = pJooq.insertInto(BcUser.BC_USER, BcUser.BC_USER.NAME).values("test_").getSQL(ParamType.INLINED);
		
		for(int i = 0; i < 100000; i++) {
			String _query = pJooq.insertInto(BcUser.BC_USER, BcUser.BC_USER.NAME).values("test_" + i).getSQL(ParamType.INLINED);
			System.out.println(" " + _query);
			batch.add(_query);			
		}
		 
		batch.execute().log(null, Level.ALL).doOnComplete(()->{
			System.out.println("TEST");
			long elapsedTime = System.nanoTime() - startTime;
		      
	        System.out.println("Total execution time to create 100K objects in Java in sec: "
	                + elapsedTime/(1000000*1000));
		}).blockLast();
		/*
	
		ArrayList<ShsUser> users = new ArrayList<>();
		for(int i = 0; i < 100000; i++) {
			users.add(new ShsUser());
		}
		userRepository.saveAll(users).log(null, Level.ALL).doOnError((err)->{
			System.out.print(err);
		}).subscribe((u)-> {
			System.out.println(u.getId());
		});*/
		return Mono.just(new ShsUser());//userRepository.save(new ShsUser());
	}
}
