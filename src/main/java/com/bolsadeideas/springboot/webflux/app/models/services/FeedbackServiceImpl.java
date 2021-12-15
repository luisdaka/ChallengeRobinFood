package com.bolsadeideas.springboot.webflux.app.models.services;

import com.bolsadeideas.springboot.webflux.app.models.dao.FeedbackDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Feedback;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{

    private static final Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    private FeedbackDao dao;

    @Override
    public Flux<Feedback> findAll() {
        return dao.findAll().
        doOnNext(m -> log.info(m.getName()));
    }

    @Override
    public Mono<Feedback> findById(String id) {
        return dao.findById(id).
                doOnNext(m -> log.info(m.getName()));
    }

    @Override
    public Mono<Feedback> save(Feedback feedback) {
        return dao.save(feedback).
                doOnNext(m -> log.info(m.getName()));

    }

    @Override
    public Mono<Void> delete(String id) {
        return dao.deleteById(id);
    }

}
