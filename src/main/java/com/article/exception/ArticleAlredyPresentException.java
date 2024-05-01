package com.article.exception;

public class ArticleAlredyPresentException extends Exception {
	
	private String message;
	
	public ArticleAlredyPresentException() {
		super();
	}

	public ArticleAlredyPresentException(String message) {
		
		super(message);
	}
	
	

}
