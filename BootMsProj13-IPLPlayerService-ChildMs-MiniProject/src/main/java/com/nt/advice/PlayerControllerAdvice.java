package com.nt.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class PlayerControllerAdvice {
	
	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<String>  handleIAE(PlayerNotFoundException iae){
		log.error(" Handling PlayerNotFoundException");
		return  new ResponseEntity<String>(iae.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String>  handleAllExceptions(Exception e){
		log.error(" Handling all unknown Exception");
		return  new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
