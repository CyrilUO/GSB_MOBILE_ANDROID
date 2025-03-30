package com.example.mobile.data.api.comment;

import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.model.comment.Comment;

import java.util.List;

import retrofit2.Call;

public class CommentRepository {

    private final CommentService commentService;

    public CommentRepository() {
        commentService = RetrofitClient.getClient().create(CommentService.class);
    }

    public Call<List<Comment>> getAllComments() {
        return commentService.getAllComments();
    }

    public Call<Comment> getCommentById(int commentId) {
        return commentService.getCommentById(commentId);
    }

    public Call<Comment> postComment(Comment comment) {
        return commentService.postComment(comment);
    }

    public Call<Comment> updateComment(int commentId, Comment comment) {
        return commentService.updateComment(commentId, comment);
    }

    public Call<Void> deleteComment(int commentId) {
        return commentService.deleteComment(commentId);
    }
}
