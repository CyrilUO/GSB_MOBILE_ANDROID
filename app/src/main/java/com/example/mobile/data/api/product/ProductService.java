package com.example.mobile.data.api.product;

import com.example.mobile.data.model.product.Product;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Part;

public interface ProductService {

    @GET("/api/products/all")
    Call<List<Product>> getAllProducts();

    @GET("/api/products/{product_id}")
    Call<Product> getProductById(@Path("product_id") int productId);

    @POST("/api/products/create-product")
    Call<Product> createProduct(@Body Product product);

    @PUT("/api/products/update-product/{product_id}")
    Call<Product> updateProduct(@Path("product_id") int productId, @Body Product product);

    @DELETE("/api/products/delete-product/{product_id}")
    Call<Void> deleteProduct(@Path("product_id") int productId);

    @Multipart
    @POST("/api/products/upload-img")
    Call<Void> uploadImage(@Part MultipartBody.Part image);

    @GET("/api/products/get-image/{product_id}")
    Call<byte[]> getImage(@Path("product_id") int productId);
}
