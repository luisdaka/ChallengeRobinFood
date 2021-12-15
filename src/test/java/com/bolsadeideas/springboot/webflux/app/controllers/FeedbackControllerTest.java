package com.bolsadeideas.springboot.webflux.app.controllers;

import com.bolsadeideas.springboot.webflux.app.models.dao.FeedbackDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Feedback;
import com.bolsadeideas.springboot.webflux.app.models.services.FeedbackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
@WebFluxTest(FeedbackController.class)
@Import(FeedbackService.class)
@AutoConfigureWebTestClient
@SpringBootTest
public class FeedbackControllerTest {

    @Mock
    FeedbackDao dao;

    @Mock
    FeedbackService service;

    private WebTestClient webClient = WebTestClient
            .bindToWebHandler(exchange -> exchange.getResponse().setComplete())
            .configureClient().build();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
   public void testCreateFeedBack() {

        Feedback feedback = Feedback.builder()
                .id("1")
                .name("Luiskk")
                .createAt(new Date())
                .email("luis")
                .build();

        Mockito.when(service.save(any())).thenReturn(Mono.just(feedback));
        Mockito.when(dao.save(any())).thenReturn(Mono.just(feedback));

        webClient.post()
                .uri("/feedback/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(feedback), Feedback.class)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void testFindFeedBack()
    {
        Feedback feedback = Feedback.builder()
                .id("1")
                .name("Luiskk")
                .createAt(new Date())
                .email("luis")
                .build();
        Mockito.when(service.findById("1"))
                .thenReturn(Mono.just(feedback));

        webClient.get().uri("/feedback/get/{id}", "1")
                .exchange()
                .expectStatus().isOk();
                /*.expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.id").isEqualTo("1")
       */
    }

    @Test
    public void testDeleteFeedBack()
    {
        Mono<Void> voidReturn  = Mono.empty();
        Mockito.when(service.delete("1"))
                .thenReturn(voidReturn);

        webClient.get().uri("/feedback/delete/{id}", "1")
                .exchange()
                .expectStatus().isOk();
    }



}
