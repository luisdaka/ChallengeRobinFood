package com.bolsadeideas.springboot.webflux.app.controllers;


import com.bolsadeideas.springboot.webflux.app.models.documents.Feedback;
import com.bolsadeideas.springboot.webflux.app.models.services.FeedbackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {

	private FeedbackService service;
	private static final Logger log = LoggerFactory.getLogger(FeedbackController.class);

	@ApiOperation(value = "Method to Get all feedbacks")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "All feedbacks obtained successfully"),
			@ApiResponse(code = 404, message = "not found Url"),
			@ApiResponse(code = 500, message = "Internal error")})
	@GetMapping(path ="/list")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Feedback> list(){
        Flux<Feedback> feedback = service.findAll()
        		.doOnNext(m -> log.info(m.getName()));
        return feedback;
	}
	/**
	 * Method to save a Feedbacks
	 * @param feedback {@link Feedback}
	 * @return ResponseEntity {@link ResponseEntity}
	 */
	@ApiOperation(value = "Method to Save a Feedback")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "feedback save successfully"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	@PostMapping(path ="/add", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Feedback feedback){
		if (feedback.getCreateAt() == null) {
			feedback.setCreateAt(new Date());
		}
		service.save(feedback)
				.doOnNext(m -> log.info(m.getName())).subscribe();
	}

	/**
	 * Method to find Feedback by id
	 * @param id {@link String}
	 * @return Mono<Feedback> {@link Mono<Feedback>}
	 */
	@ApiOperation(value = "Method to find Feedback by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Feedback found"),
			@ApiResponse(code = 404, message = "Feedback not found "),
			@ApiResponse(code = 500, message = "Internal error")})
	@GetMapping(path ="get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<Feedback> findById(@PathVariable String id){
		 Mono<Feedback> Feedback = service.findById(id)
				 .doOnNext(m -> log.info(m.getName() + "has been found"));
		return  Feedback;
	}

	/**
	 * Method to Delete Feedback by Id
	 * @param id {@link String}
	 */
	@ApiOperation(value = "Find Feedback by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Feedback deleted"),
			@ApiResponse(code = 404, message = "Feedback not found "),
			@ApiResponse(code = 500, message = "Internal error")})
	@DeleteMapping(path ="delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable String id){
		service.delete(id)
				.doOnNext(m -> log.info("Feedbacks has been found")).subscribe();
	}

}
