package com.example.mobile.data.api.product;

import com.example.mobile.data.api.RetrofitClient;
import com.example.mobile.data.model.product.Product;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;

public class ProductRepository {

    private final ProductService productService;

    public ProductRepository() {
        productService = RetrofitClient.getClient().create(ProductService.class);
    }

    public Call<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    public Call<Product> getProductById(int productId) {
        return productService.getProductById(productId);
    }

    public Call<Product> createProduct(Product product) {
        return productService.createProduct(product);
    }

    public Call<Product> updateProduct(int productId, Product product) {
        return productService.updateProduct(productId, product);
    }

    public Call<Void> deleteProduct(int productId) {
        return productService.deleteProduct(productId);
    }

    public Call<Void> uploadImage(MultipartBody.Part image) {
        return productService.uploadImage(image);
    }

    public Call<byte[]> getImage(int productId) {
        return productService.getImage(productId);
    }
}
