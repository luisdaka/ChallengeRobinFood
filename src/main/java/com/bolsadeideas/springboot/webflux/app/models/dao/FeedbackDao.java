package com.bolsadeideas.springboot.webflux.app.models.dao;

import com.bolsadeideas.springboot.webflux.app.models.documents.Feedback;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FeedbackDao extends ReactiveMongoRepository<Feedback, String>{

}
