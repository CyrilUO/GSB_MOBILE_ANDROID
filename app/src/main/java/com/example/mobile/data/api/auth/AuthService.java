package com.example.mobile.data.api.auth;

import com.example.mobile.data.model.tokens.TokenResponse;
import com.example.mobile.data.model.user.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/api/auth/login")
    Call<TokenResponse> login(@Body UserLogin userLogin);

    @DELETE("/api/auth/logout")
    Call<Void> logout();
}
