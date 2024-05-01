package com.article.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.article.entity.Article;
import com.article.exception.ArticleAlredyPresentException;
import com.article.exception.ArticleNotFoundException;
import com.article.repo.ArticleRepository;
import com.article.utils.EmailUtils;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public Article saveArticle(Article article) throws ArticleAlredyPresentException {
		if (articleRepository.existsById(article.getId())) {
			throw new ArticleAlredyPresentException("article is alredy present");
		}

		article.setStatus("active");
		article.setPublishedOnConnect("published");
		Article savedArticle = articleRepository.save(article);
		return savedArticle;
	}

	@Override
	public List<Article> getAllArticles() {
		List<Article> all = articleRepository.findAll();
		return all;
	}

	@Override
	public Article updateArticle(Integer id, Article article) throws ArticleNotFoundException {

		if (articleRepository.findById(id).isEmpty()) {
			throw new ArticleNotFoundException("Article is not found");
		} else {
			article.setId(id);
			Article updatedArticle = articleRepository.save(article);
			return updatedArticle;
		}
	}

	@Override
	public String deleteArticle(Integer id) throws ArticleNotFoundException {
		if (articleRepository.findById(id).isEmpty()) {
			throw new ArticleNotFoundException("Article not found");
		} else {
			articleRepository.deleteById(id);
			return "Article Deleted Successfully";

		}

	}

	@Override
	public boolean sendArticlesthroughMail(String email) throws Exception {
		List<Article> list = articleRepository.findAll();

		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("Data");
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Title Of Article");
		headerRow.createCell(1).setCellValue("Description");
		headerRow.createCell(2).setCellValue("Article Date");
		headerRow.createCell(3).setCellValue("Author");
		headerRow.createCell(4).setCellValue("Status");
		headerRow.createCell(5).setCellValue("Published or not");

		int rowIndex = 1;

		for (Article record : list) {

			HSSFRow dataRow = sheet.createRow(rowIndex);
			dataRow.createCell(0).setCellValue(record.getTitleOfArticle());
			dataRow.createCell(1).setCellValue(record.getDescription());
			dataRow.createCell(2).setCellValue(record.getArticleDate().toString());
			dataRow.createCell(3).setCellValue(record.getAuthor());
			dataRow.createCell(4).setCellValue(record.getStatus());
			dataRow.createCell(5).setCellValue(record.getPublishedOnConnect());

			rowIndex++;
		}

		File file = new File("data.xls");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		hssfWorkbook.write(fileOutputStream);

		String subject = "Article Details";
		String body = "Please find below attached article file for your reference";

		boolean sendEmail = emailUtils.sendEmail(email, subject, body, file);

		hssfWorkbook.close();

		return sendEmail;
	}

}
