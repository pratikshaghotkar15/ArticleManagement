package com.article.service;

import java.util.List;

import com.article.entity.Article;
import com.article.exception.ArticleAlredyPresentException;
import com.article.exception.ArticleNotFoundException;


public interface ArticleService {
	
	public Article saveArticle(Article article) throws ArticleAlredyPresentException;
	
	public List<Article> getAllArticles();
	
	public Article updateArticle(Integer id,Article article) throws ArticleNotFoundException;
	
	public String deleteArticle(Integer id) throws ArticleNotFoundException;
	
	public boolean sendArticlesthroughMail(String email) throws Exception;
	

}
