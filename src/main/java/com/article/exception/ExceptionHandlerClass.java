package com.article.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerClass {
	
	
	@ExceptionHandler(value =ArticleAlredyPresentException.class)
	public ResponseEntity<ErrorResponse> handleArticleAlredyPresentException(ArticleAlredyPresentException ar){
		
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorResponse("a100");
		errorResponse.setErrormsg(ar.getMessage());
		errorResponse.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(value =ArticleNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleArticleNotFoundException(ArticleNotFoundException an){
		
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorResponse("a101");
		errorResponse.setErrormsg(an.getMessage());
		errorResponse.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value =smtpException.class)
	public ResponseEntity<ErrorResponse> handleArithmeticException(smtpException sme){
		
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorResponse("SMTP100");
		errorResponse.setErrormsg(sme.getMessage());
		errorResponse.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
