// AuthRepository.java

package com.example.mobile.data.api.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.model.tokens.TokenResponse;
import com.example.mobile.data.model.user.UserLogin;
import com.example.mobile.data.model.login.LoginResult;
import com.example.mobile.utils.JwtUtils;
import com.example.mobile.utils.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final AuthService authService;
    private final SharedPreferences sharedPreferences;

    private final Context context;

    public AuthRepository(Context context) {
        this.context = context;
        authService = RetrofitClient.getClient().create(AuthService.class);
        sharedPreferences = context.getSharedPreferences("gsb_prefs", Context.MODE_PRIVATE);
    }

    public void login(UserLogin userLogin, Callback<LoginResult> callback) {
        Call<TokenResponse> call = authService.login(userLogin);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getAccessToken();
                    saveToken(token);
                    RetrofitClient.setToken(token);

                    String role = JwtUtils.getRoleFromToken(token);

                    if (role != null) {
                        SharedPrefManager.getInstance(context).saveRole(role);
                    }


                    // Convertir TokenResponse en LoginResult
                    LoginResult loginResult = new LoginResult(true);

                    // Appeler le callback avec LoginResult
                    callback.onResponse(null, Response.success(loginResult));
                } else {
                    LoginResult loginResult = new LoginResult(false, "Erreur de connexion");
                    callback.onResponse(null, Response.success(loginResult));
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                LoginResult loginResult = new LoginResult(false, t.getMessage());
                callback.onResponse(null, Response.success(loginResult));
            }
        });
    }

    private void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("access_token", token);
        editor.apply();
    }

    public Call<Void> logout() {
        return authService.logout();
    }
}
