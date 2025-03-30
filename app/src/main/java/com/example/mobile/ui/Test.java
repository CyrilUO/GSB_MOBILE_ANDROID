package com.example.mobile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.api.auth.AuthRepository;
import com.example.mobile.data.api.product.ProductService;
import com.example.mobile.data.api.user.UserService;
import com.example.mobile.data.model.product.Product;
import com.example.mobile.data.model.tokens.TokenResponse;
import com.example.mobile.data.model.user.User;
import com.example.mobile.data.model.user.UserLogin;
import com.example.mobile.utils.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    private TextView textView;
    private AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_test); // Assure-toi que ton layout s'appelle bien "activity_test.xml"

        textView = findViewById(R.id.textView);
        authRepository = new AuthRepository();

        String savedToken = SharedPrefManager.getInstance(getApplicationContext()).getToken();
        if (savedToken != null) {
            RetrofitClient.setToken(savedToken);
            Log.d(TAG, "Token chargé depuis SharedPreferences : " + savedToken);
        } else {
            Log.d(TAG, "Aucun token trouvé dans SharedPreferences.");
        }

        loginAndFetchUsers();
    }

    private void loginAndFetchUsers() {
        // Remplace par un compte qui existe déjà sur ton backend
        UserLogin userLogin = new UserLogin("admin@admin.admin", "admin");

        authRepository.login(userLogin, new Callback<>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getAccessToken();
                    SharedPrefManager.getInstance(getApplicationContext()).saveToken(token);
                    RetrofitClient.setToken(token);
                    Log.d(TAG, "Login successful! Token: " + token);

                    fetchUsers();
                    fetchProducts();
                } else {
                    textView.setText("Login failed! Error code: " + response.code());
                    Log.e(TAG, "Login failed! Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                textView.setText("Login failed! Error: " + t.getMessage());
                Log.e(TAG, "Login failed! Error: " + t.getMessage());
            }
        });

    }

    private void fetchUsers() {
        UserService userService = RetrofitClient.getClient().create(UserService.class);

        Call<List<User>> call = userService.getAllUsers();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();
                    StringBuilder result = new StringBuilder();

                    for (User user : users) {
                        result.append("ID: ").append(user.getId()).append("\n")
                                .append("Username: ").append(user.getUsername()).append("\n")
                                .append("Email: ").append(user.getEmail()).append("\n")
                                .append("Role: ").append(user.getRole()).append("\n")
                                .append("Profile Picture URL: ").append(user.getProfilePictureUrl()).append("\n")
                                .append("Created At: ").append(user.getCreatedAt()).append("\n")
                                .append("Modified At: ").append(user.getModifiedAt()).append("\n")
                                .append("----------------------------\n\n");
                    }

                    textView.setText(result.toString());
                    Log.d(TAG, "API Call Successful! Users retrieved: " + users.size());
                } else {
                    textView.setText("API Call Failed! Error code: " + response.code());
                    Log.e(TAG, "API Call Failed! Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                textView.setText("API Call Failed! Error: " + t.getMessage());
                Log.e(TAG, "API Call Failed! Error: " + t.getMessage());
            }
        });
    }

    private void fetchProducts() {
        ProductService productService = RetrofitClient.getClient().create(ProductService.class);

        Call<List<Product>> call = productService.getAllProducts();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();
                    StringBuilder result = new StringBuilder();

                    for (Product product : products) {
                        result.append("ID: ").append(product.getId()).append("\n")
                                .append("Name: ").append(product.getName()).append("\n")
                                .append("Description: ").append(product.getDescription()).append("\n")
                                .append("Price: ").append(product.getPrice()).append("\n")
                                .append("Category ID: ").append(product.getCategoryId()).append("\n")
                                .append("----------------------------\n\n");
                    }

                    Log.d(TAG, "API Call Successful! Products retrieved: " + products.size());
                } else {
                    Log.e(TAG, "API Call Failed! Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "API Call Failed! Error: " + t.getMessage());
            }
        });
    }
}
