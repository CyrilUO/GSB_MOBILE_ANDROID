package com.example.mobile.data.api.article;

import com.example.mobile.data.model.article.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArticleService {

    @GET("/api/article/all")
    Call<List<Article>> getAllArticles();

    @GET("/api/article/{article_id}")
    Call<Article> getArticleById(@Path("article_id") int articleId);

    @GET("/api/article/product/{product_id}")
    Call<List<Article>> getArticlesByProduct(@Path("product_id") int productId);

    @GET("/api/article/user/{user_id}")
    Call<List<Article>> getArticlesByUser(@Path("user_id") int userId);

    @POST("/api/article/create-article")
    Call<Article> createArticle(@Body Article article);

    @DELETE("/api/article/delete-article/{article_id}")
    Call<Void> deleteArticle(@Path("article_id") int articleId);

    @PUT("/api/article/update-article/{article_id}")
    Call<Article> updateArticle(@Path("article_id") int articleId, @Body Article article);
}
