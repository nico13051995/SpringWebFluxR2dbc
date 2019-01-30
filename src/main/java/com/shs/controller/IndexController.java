package com.shs.controller;

import java.util.ArrayList;
import java.util.logging.Level;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.web.bind.annotation.RestController;

import com.shs.model.ShsUser;
import com.shs.repo.UserRepository;

import reactor.core.publisher.Mono;

@RestController
public class IndexController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DatabaseClient databaseClient;

	@PostConstruct
	public Mono<ShsUser> create(){
		/*userRepository.findAll().doOnError((err)->{
			System.out.println(err);
		}).subscribe((u)->{
			System.out.println(u.toString());
		});*/
		
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
