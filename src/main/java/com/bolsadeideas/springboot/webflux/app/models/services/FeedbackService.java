package com.bolsadeideas.springboot.webflux.app.models.services;

import com.bolsadeideas.springboot.webflux.app.models.documents.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackService {
	
	public Flux<Feedback> findAll();
	public Mono<Feedback> findById(String id);
	public Mono<Feedback> save(Feedback feedback);
	public Mono<Void> delete(String id);

}
