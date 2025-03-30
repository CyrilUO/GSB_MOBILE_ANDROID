package com.example.mobile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.api.user.UserService;
import com.example.mobile.data.model.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_test);

        textView = findViewById(R.id.textView);

        testApiCall();
    }

    private void testApiCall() {
        UserService userService = RetrofitClient.getClient().create(UserService.class);

        Call<List<User>> call = userService.getAllUsers();  // Assure-toi que cet endpoint existe bien

        call.enqueue(new Callback<List<User>>() {
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
                                .append("Modifed_at: ").append(user.getModifiedAt()).append("\n")
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
}
