package com.shs.controller;

import java.util.logging.Level;

import javax.annotation.PostConstruct;

import com.shs.codegen.maven.jooq.tables.BcUser;
import com.shs.codegen.maven.jooq.tables.BcVisitRequest;
import com.shs.codegen.maven.jooq.tables.records.BcVisitRequestRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.conf.ParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.web.bind.annotation.RestController;

import com.shs.model.ShsUser;
import com.shs.repo.UserRepository;

import io.r2dbc.postgresql.PostgresqlBatch;
import io.r2dbc.postgresql.PostgresqlConnection;
import reactor.core.publisher.Mono;

import static org.jooq.impl.DSL.condition;

@RestController
public class IndexController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DSLContext pJooq;

	@PostConstruct
	public Mono<ShsUser> create(){

		BcVisitRequestRecord visitRequestCriteria = new BcVisitRequestRecord();
		visitRequestCriteria.setUserId(1L);
		Result<BcVisitRequestRecord> result = pJooq.selectFrom(BcVisitRequest.BC_VISIT_REQUEST).where(condition(visitRequestCriteria)).fetch();

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
