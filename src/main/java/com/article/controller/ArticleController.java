package com.article.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.article.entity.Article;
import com.article.exception.ArticleAlredyPresentException;
import com.article.exception.ArticleNotFoundException;
import com.article.service.ArticleService;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "articlecontroller", description = "here all articles managed")
public class ArticleController {
	
	private static final Logger logInfo=(Logger) LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@PostMapping("/articles")
	public ResponseEntity<Article> createArticle(@RequestBody Article article)throws ArticleAlredyPresentException {

		Article saveArticle = articleService.saveArticle(article);

		logInfo.info("Article is saved");
		
		return new ResponseEntity<>(saveArticle, HttpStatus.CREATED);

	}

	@GetMapping("/articles")
	public ResponseEntity<List<Article>> getAllArticles() {

		List<Article> allArticles = articleService.getAllArticles();
		
		logInfo.info("All Articles is retrived");

		return new ResponseEntity<>(allArticles, HttpStatus.OK);

	}

	@PutMapping("/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable("id") Integer id, @RequestBody Article article) throws ArticleNotFoundException {

		Article updatedArticle = articleService.updateArticle(id, article);
		
		logInfo.info("Article is updated");

		return new ResponseEntity<>(updatedArticle, HttpStatus.OK);

	}

	@DeleteMapping("/articles/{id}")
	public ResponseEntity<String> updateArticle(@PathVariable("id") Integer id)throws ArticleNotFoundException {

		String deletedArticle = articleService.deleteArticle(id);
		
		logInfo.info("Article is deleted");

		return new ResponseEntity<>(deletedArticle, HttpStatus.OK);

	}
	
	@GetMapping("/articles/sendviaemail/{email}")
	public ResponseEntity<String> sendReportViaEmail(@PathVariable("email")String email) throws Exception{
		
		boolean sendArticlesthroughMail = articleService.sendArticlesthroughMail(email);
		
		if (sendArticlesthroughMail) {
			logInfo.info("Articles mailed succesfuuly");
			return new ResponseEntity<>("email send successfully",HttpStatus.OK);
			
		}
		else {
			logInfo.info("articles are not mailed ");
			return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
					
		}
		
	}

}
