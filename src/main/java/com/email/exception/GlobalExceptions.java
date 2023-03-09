package com.email.exception;

import java.util.List;
import javax.mail.AuthenticationFailedException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.email.common.LoggingResponseMessage;
import com.email.common.MessageTypeConst;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import lombok.extern.slf4j.Slf4j;

/**
 * This is Global exception handler class
 * its handle all type of exception
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptions extends ResponseEntityExceptionHandler {

	@Autowired
	Gson gson; 
	
	@Value("${some.error}")
	String message;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
		ErrorResponse response = ErrorResponse.builder()
				.message(fieldError.get(0).getDefaultMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();

		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message("Some data type errror.")
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));

		return ResponseEntity.internalServerError().body(response);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmailException.class)
	public ErrorResponse handleRecordFoundException(EmailException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ClassCastException.class)
	public ErrorResponse handleClassCastException(ClassCastException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ErrorResponse handleNullPointerException(NullPointerException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(AuthenticationFailedException.class)
	public ErrorResponse handleAuthenticationFailedException(AuthenticationFailedException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(JsonSyntaxException.class)
	public ErrorResponse handleJsonSyntaxException(JsonSyntaxException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IllegalStateException.class)
	public ErrorResponse handleIllegalStateException(IllegalStateException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(JsonIOException.class)
	public ErrorResponse handleJsonIOException(JsonIOException ex) {
		String msg= ex.getMessage();
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(msg)
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		return response;
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ErrorResponse response=null;
		response = ErrorResponse.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();

		LoggingResponseMessage loggingResponseMessage2 = LoggingResponseMessage.builder()
				.data(response)
				.message(message)
				.messageTypeId(MessageTypeConst.INTERNAL_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		log.error(gson.toJson(loggingResponseMessage2));
		
		return ResponseEntity.internalServerError().body(response);
	}
	
}
