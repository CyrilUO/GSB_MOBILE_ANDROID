package com.example.mobile.utils;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class JwtUtils {

    public static String getRoleFromToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return null;

            String payload = new String(Base64.decode(parts[1], Base64.DEFAULT), "UTF-8");
            JSONObject jsonObject = new JSONObject(payload);

            return jsonObject.getString("role");
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
