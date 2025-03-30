package com.example.mobile.data.api.article;

import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.model.article.Article;

import java.util.List;

import retrofit2.Call;

public class ArticleRepository {

    private final ArticleService articleService;

    public ArticleRepository() {
        articleService = RetrofitClient.getClient().create(ArticleService.class);
    }

    public Call<List<Article>> getAllArticles() {
        return articleService.getAllArticles();
    }

    public Call<Article> getArticleById(int articleId) {
        return articleService.getArticleById(articleId);
    }

    public Call<List<Article>> getArticlesByProduct(int productId) {
        return articleService.getArticlesByProduct(productId);
    }

    public Call<List<Article>> getArticlesByUser(int userId) {
        return articleService.getArticlesByUser(userId);
    }

    public Call<Article> createArticle(Article article) {
        return articleService.createArticle(article);
    }

    public Call<Void> deleteArticle(int articleId) {
        return articleService.deleteArticle(articleId);
    }

    public Call<Article> updateArticle(int articleId, Article article) {
        return articleService.updateArticle(articleId, article);
    }
}
