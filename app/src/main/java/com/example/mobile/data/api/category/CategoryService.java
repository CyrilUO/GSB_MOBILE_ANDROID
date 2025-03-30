package com.example.mobile.data.api.category;

import com.example.mobile.data.model.categories.Category;
import com.example.mobile.data.model.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("/api/category/all")
    Call<List<Category>> getAllCategories();

    @GET("/api/category/{category_id}")
    Call<Category> getCategoryById(@Path("category_id") int categoryId);

    @GET("/api/category/{category_id}/products")
    Call<List<Product>> getProductsByCategory(@Path("category_id") int categoryId);

    @POST("/api/category/create-category")
    Call<Category> createCategory(@Body Category category);

    @PUT("/api/category/update-category/{category_id}")
    Call<Category> updateCategory(@Path("category_id") int categoryId, @Body Category category);

    @DELETE("/api/category/delete-category/{category_id}")
    Call<Void> deleteCategory(@Path("category_id") int categoryId);
}
