package com.Food.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Food.exception.FoodException;

@RestControllerAdvice
public class ExceptionHandlingControllerAdvice {

	@Autowired
	private Environment  environment;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidExceptions(MethodArgumentNotValidException ex){
		ErrorMessage error=new ErrorMessage();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getBindingResult().getAllErrors().stream().map(oe -> oe.getDefaultMessage()).collect(Collectors.joining(", ")));
		error.setTimeStamp(LocalDateTime.now().toString());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleValidConstraintViolationExceptions(ConstraintViolationException ex){
		ErrorMessage error=new ErrorMessage();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.joining(", ")));
		error.setTimeStamp(LocalDateTime.now().toString());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FoodException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(FoodException exception){
		ErrorMessage error=new ErrorMessage();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(environment.getProperty(exception.getMessage()));
		error.setTimeStamp(LocalDateTime.now().toString());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleExceptions(Exception exception){
		ErrorMessage error=new ErrorMessage();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Something went wrong! ! ! ! ! ! !   "+ exception.getMessage());
		error.setTimeStamp(LocalDateTime.now().toString());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
