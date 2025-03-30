package com.example.mobile.data.api.user;

import com.example.mobile.data.model.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("/api/users/create-user")
    Call<User> createUser(@Body User user);

    @GET("/api/users/all")
    Call<List<User>> getAllUsers();

    @GET("/api/users/{user_id}")
    Call<User> getUser(@Path("user_id") int userId);

    @DELETE("/api/users/delete-user/{user_id}")
    Call<Void> deleteUser(@Path("user_id") int userId);

    @PUT("/api/users/update-user/{user_id}")
    Call<User> updateUser(@Path("user_id") int userId, @Body User user);
}
