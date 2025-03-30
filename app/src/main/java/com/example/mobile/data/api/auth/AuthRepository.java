package com.example.mobile.data.api.auth;

import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.model.tokens.TokenResponse;
import com.example.mobile.data.model.user.UserLogin;

import retrofit2.Call;

public class AuthRepository {

    private final AuthService authService;

    public AuthRepository() {
        authService = RetrofitClient.getClient().create(AuthService.class);
    }

    public Call<TokenResponse> login(UserLogin userLogin) {
        return authService.login(userLogin);
    }

    public Call<Void> logout() {
        return authService.logout();
    }
}
