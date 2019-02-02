package com.shs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.annotation.PostConstruct;


import com.shs.codegen.maven.jooq.Keys;
import com.shs.codegen.maven.jooq.tables.BcVisitRequest;
import com.shs.codegen.maven.jooq.tables.records.BcVisitRequestRecord;
import com.shs.dto.BcUserDTO;
import com.shs.services.UserService;
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
    UserService userService;

	@PostConstruct
	public Mono<ShsUser> create(){
		List<BcUserDTO> users = new ArrayList<>();
		users.add(new BcUserDTO(12L,"Roman Zinchuk"));
		users.add(new BcUserDTO(13L,"Mr Zigzag"));
		users.add(new BcUserDTO(14L,"Zigzagus"));
        userService.saveAll(users);
        //
		List<BcUserDTO> models = userService.findAll();
		return Mono.just(new ShsUser());//userRepository.save(new ShsUser());
	}
}
