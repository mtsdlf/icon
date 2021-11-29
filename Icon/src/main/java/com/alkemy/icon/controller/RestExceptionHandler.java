package com.alkemy.icon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alkemy.icon.dto.ApiErrorDTO;
import com.alkemy.icon.exceptions.ParamNotFound;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {ParamNotFound.class})
	protected ResponseEntity<Object> HandleParamNotFound(RuntimeException ex, WebRequest request) {
		ApiErrorDTO apiError = new ApiErrorDTO(
				HttpStatus.NOT_FOUND,
				ex.getMessage(),
				Arrays.asList("Param Not Found")
		);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + " : " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + " : " + error.getDefaultMessage());
		}	
		ApiErrorDTO apiError = new ApiErrorDTO(
				HttpStatus.BAD_REQUEST,
				ex.getLocalizedMessage(),
				errors);
		
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}
	
	/*
	 * @Override protected ResponseEntity<Object> handleMethodArgumentNotValid(
	 * MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
	 * WebRequest request) { Map<String, String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) ->{
	 * 
	 * String fieldName = ((FieldError) error).getField(); String message =
	 * error.getDefaultMessage(); errors.put(fieldName, message); }); return new
	 * ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST); }
	 */
	
	@ExceptionHandler(value= {IllegalArgumentException.class, IllegalStateException.class})
	public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be aaplication specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	
	}
}
