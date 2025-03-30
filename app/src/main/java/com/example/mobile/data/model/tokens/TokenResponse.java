package com.example.mobile.data.model.tokens;

import com.google.gson.annotations.SerializedName;


public class TokenResponse {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken(){
        return accessToken;
    }

    public String getTokenType() {
        return accessToken;
    }
}
