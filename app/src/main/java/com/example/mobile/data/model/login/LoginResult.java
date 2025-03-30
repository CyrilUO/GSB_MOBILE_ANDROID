// LoginResult.java

package com.example.mobile.data.model.login;

public class LoginResult {

    private boolean success;
    private String errorMessage;

    // Constructeur en cas de succès
    public LoginResult(boolean success) {
        this.success = success;
        this.errorMessage = null;
    }

    // Constructeur en cas d'erreur
    public LoginResult(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
