package com.article.exception;

public class ArticleNotFoundException extends Exception {
	
private String message;
	
	public ArticleNotFoundException() {
		super();
	}

	public ArticleNotFoundException(String message) {
		
		super(message);
	}

}
