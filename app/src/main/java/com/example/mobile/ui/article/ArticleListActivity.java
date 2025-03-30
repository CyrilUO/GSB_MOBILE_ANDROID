// ArticleListActivity.java

package com.example.mobile.ui.article;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.api.auth.AuthRepository;
import com.example.mobile.ui.login.LoginActivity;
import com.example.mobile.utils.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleListActivity extends AppCompatActivity {

    private static final String TAG = "ArticleListActivity";
    private AuthRepository authRepository;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_list_activity);

        authRepository = new AuthRepository(this);
        ImageButton buttonLogout = findViewById(R.id.buttonLogout);
        ImageButton btnSearch = findViewById(R.id.btnSearch);
        ImageButton btnWrite = findViewById(R.id.btnWrite);
        ImageButton btnSettings = findViewById(R.id.btnSettings);

        String userRole = SharedPrefManager.getInstance(this).getRole();
        if ("editor".equals(userRole)){
            btnSettings.setVisibility(View.GONE);
        }

        if("user".equals(userRole)){
            btnSettings.setVisibility(View.GONE);
            btnWrite.setVisibility(View.GONE);
        }

        Toast.makeText(this, "Rôle de l'utilisateur : " + userRole, Toast.LENGTH_SHORT).show();


        buttonLogout.setOnClickListener(v -> {
            Toast.makeText(ArticleListActivity.this, "Déconnexion effectuée", Toast.LENGTH_SHORT).show();
            logoutUser();
        });

        btnSearch.setOnClickListener(v ->
                Toast.makeText(ArticleListActivity.this, "Recherche de produits", Toast.LENGTH_SHORT).show()
        );

        btnWrite.setOnClickListener(v ->
                Toast.makeText(ArticleListActivity.this, "Création d'un nouvel article", Toast.LENGTH_SHORT).show()
        );

        btnSettings.setOnClickListener(v ->
                Toast.makeText(ArticleListActivity.this, "Accès aux Paramètres", Toast.LENGTH_SHORT).show()
        );
    }

    private void logoutUser() {
        Call<Void> call = authRepository.logout();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ArticleListActivity.this, "Déconnexion réussie", Toast.LENGTH_SHORT).show();

                    SharedPrefManager.getInstance(getApplicationContext()).clearToken();
                    RetrofitClient.setToken(null);

                    Intent intent = new Intent(ArticleListActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ArticleListActivity.this, "Erreur lors de la déconnexion", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Déconnexion échouée : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ArticleListActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Erreur réseau : " + t.getMessage());
            }
        });
    }
}
