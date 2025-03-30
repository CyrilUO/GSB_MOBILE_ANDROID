package com.example.mobile.data.api.comment;

import com.example.mobile.data.model.comment.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentService {

    @GET("/api/comment/all")
    Call<List<Comment>> getAllComments();

    @GET("/api/comment/{comment_id}")
    Call<Comment> getCommentById(@Path("comment_id") int commentId);

    @POST("/api/comment/create-comment")
    Call<Comment> postComment(@Body Comment comment);

    @PUT("/api/comment/update-comment/{comment_id}")
    Call<Comment> updateComment(@Path("comment_id") int commentId, @Body Comment comment);

    @DELETE("/api/comment/delete-comment/{comment_id}")
    Call<Void> deleteComment(@Path("comment_id") int commentId);
}
