//package com.example.mobile.ui;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.mobile.R;
//import com.example.mobile.data.api.auth.AuthService;
//import com.example.mobile.data.api.RetrofitClient;
//import com.example.mobile.data.model.tokens.TokenResponse;
//import com.example.mobile.data.model.user.UserLogin;
//import com.example.mobile.utils.SharedPrefManager;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class LoginActivity extends AppCompatActivity {
//    private EditText emailInput, passwordInput;
//    private Button loginButton;
//    private AuthService authService;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        emailInput = findViewById(R.id.emailInput);
//        passwordInput = findViewById(R.id.passwordInput);
//        loginButton = findViewById(R.id.loginButton);
//
//        authService = RetrofitClient.getClient().create(AuthService.class);
//
//        loginButton.setOnClickListener(v -> loginUser());
//    }
//
//    private void loginUser() {
//        String email = emailInput.getText().toString().trim();
//        String password = passwordInput.getText().toString().trim();
//
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Email et mot de passe requis", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        UserLogin userLogin = new UserLogin(email, password);
//
//        authService.login(userLogin).enqueue(new Callback<TokenResponse>() {
//            @Override
//            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    String token = response.body().getAccessToken();
//                    SharedPrefManager.getInstance(LoginActivity.this).saveToken(token);
//
//                    Toast.makeText(LoginActivity.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TokenResponse> call, Throwable t) {
//                Log.e("LoginError", t.getMessage());
//                Toast.makeText(LoginActivity.this, "Erreur réseau", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
