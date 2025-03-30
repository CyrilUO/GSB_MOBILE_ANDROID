package com.example.mobile.data.api.user;

import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.model.user.User;

import java.util.List;

import retrofit2.Call;

public class UserRepository {

    private final UserService userService;

    public UserRepository() {
        userService = RetrofitClient.getClient().create(UserService.class);
    }

    public Call<User> createUser(User user) {
        return userService.createUser(user);
    }

    public Call<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    public Call<User> getUser(int userId) {
        return userService.getUser(userId);
    }

    public Call<Void> deleteUser(int userId) {
        return userService.deleteUser(userId);
    }

    public Call<User> updateUser(int userId, User user) {
        return userService.updateUser(userId, user);
    }
}
