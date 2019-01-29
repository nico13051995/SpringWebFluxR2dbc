package com.shs.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.shs.model.ShsUser;
import com.shs.repo.UserRepository;

import reactor.core.publisher.Mono;

@RestController
public class IndexController {

	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public Mono<ShsUser> create(){
		userRepository.findAll().subscribe((u)->{
			System.out.print(u.toString());
		});
		return Mono.just(new ShsUser());//userRepository.save(new ShsUser());
	}
}
