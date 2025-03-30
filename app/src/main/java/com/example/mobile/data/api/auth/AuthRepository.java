package com.example.mobile.data.api.auth;

import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.model.tokens.TokenResponse;
import com.example.mobile.data.model.user.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final AuthService authService;

    public AuthRepository() {
        authService = RetrofitClient.getClient().create(AuthService.class);
    }

    public void login(UserLogin userLogin, Callback<TokenResponse> callback) {
        Call<TokenResponse> call = authService.login(userLogin);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getAccessToken();
                    RetrofitClient.setToken(token);
                }
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public Call<Void> logout() {
        return authService.logout();
    }
}
