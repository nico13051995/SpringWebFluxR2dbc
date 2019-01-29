package com.shs.repo;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.shs.model.ShsUser;

import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<ShsUser, Integer> {

	/*@Query("select id, firstname, lastname from t_user c where c.lastname = $1")
	Flux<ShsUser> findByLastnameLike(String lastname);*/
}