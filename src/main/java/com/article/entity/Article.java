package com.article.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titleOfArticle;

	private String publicUrl;

	private String author;

	private LocalDate articleDate;

	private String description;

	private String accessCategoty;

	private LocalDate freeViewExpiryDate;

	private String status;

	private String publishedOnConnect;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitleOfArticle() {
		return titleOfArticle;
	}

	public void setTitleOfArticle(String titleOfArticle) {
		this.titleOfArticle = titleOfArticle;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getArticleDate() {
		return articleDate;
	}

	public void setArticleDate(LocalDate articleDate) {
		this.articleDate = articleDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccessCategoty() {
		return accessCategoty;
	}

	public void setAccessCategoty(String accessCategoty) {
		this.accessCategoty = accessCategoty;
	}

	public LocalDate getFreeViewExpiryDate() {
		return freeViewExpiryDate;
	}

	public void setFreeViewExpiryDate(LocalDate freeViewExpiryDate) {
		this.freeViewExpiryDate = freeViewExpiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPublishedOnConnect() {
		return publishedOnConnect;
	}

	public void setPublishedOnConnect(String publishedOnConnect) {
		this.publishedOnConnect = publishedOnConnect;
	}

}
