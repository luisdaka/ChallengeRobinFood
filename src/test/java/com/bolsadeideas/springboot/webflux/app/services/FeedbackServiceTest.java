package com.bolsadeideas.springboot.webflux.app.services;

import com.bolsadeideas.springboot.webflux.app.models.dao.FeedbackDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Feedback;
import com.bolsadeideas.springboot.webflux.app.models.services.FeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceTest {

    @InjectMocks
    FeedbackService FeedbackService;

    FeedbackDao dao = Mockito.mock(FeedbackDao.class);

    @Test
    public void delete(){
        Mono<Void> ans = Mono.empty();
        when(dao.deleteById(anyString())).thenReturn(Mono.empty());

        FeedbackService.delete("1").subscribe();
        assertEquals(FeedbackService.delete("1"),ans);
    }

    @Test
    public void save(){
        Feedback feedback =  Feedback.builder()
                .id("1").email("luis@hogmail.com").createAt(new Date()).name("Luiskk").build();
        when(dao.save(any())).thenReturn(Mono.just(feedback));

        String ans = FeedbackService.save(feedback)
                .map(Feedback::getName).block();
        assertEquals(ans,feedback.getName());
    }

    @Test
    public void findby(){
        Feedback mutant =  Feedback.builder()
                .id("1").email("luis@hogmail.com").createAt(new Date()).name("Luiskk").build();
        when(dao.findById(anyString())).thenReturn(Mono.just(mutant));
        FeedbackService.findById("1").subscribe();
        String ans = FeedbackService.findById("1")
                .map(Feedback::getName).block();
        assertEquals(ans,mutant.getName());
    }


}

