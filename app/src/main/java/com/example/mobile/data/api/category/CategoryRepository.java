package com.example.mobile.data.api.category;

import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.model.categories.Category;
import com.example.mobile.data.model.products.Product;

import java.util.List;

import retrofit2.Call;

public class CategoryRepository {

    private final CategoryService categoryService;

    public CategoryRepository() {
        categoryService = RetrofitClient.getClient().create(CategoryService.class);
    }

    public Call<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public Call<Category> getCategoryById(int categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    public Call<List<Product>> getProductsByCategory(int categoryId) {
        return categoryService.getProductsByCategory(categoryId);
    }

    public Call<Category> createCategory(Category category) {
        return categoryService.createCategory(category);
    }

    public Call<Category> updateCategory(int categoryId, Category category) {
        return categoryService.updateCategory(categoryId, category);
    }

    public Call<Void> deleteCategory(int categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
