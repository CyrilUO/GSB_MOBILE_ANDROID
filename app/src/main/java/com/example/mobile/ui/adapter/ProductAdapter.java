//package com.example.mobile.ui.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mobile.R;
//import com.example.mobile.data.model.product.Product;
//
//import java.util.List;
//
//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
//
//    private final List<Product> productList;
//    private final Context context;
//
//    public ProductAdapter(List<Product> productList, Context context) {
//        this.productList = productList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_product_card, parent, false);
//        return new ProductViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
//        Product product = productList.get(position);
//        holder.productName.setText(product.getName());
//        holder.productCategory.setText(product.getCategoryId());
//
//        // Si tu as des images URL, charge-les ici avec Glide ou Picasso
//        // Glide.with(context).load(product.getImageUrl()).into(holder.productImage);
//
//        holder.productRating.setRating(product.getRating());
//        holder.btnComment.setOnClickListener(v -> {
//            // Logique pour commenter
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    public static class ProductViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView productImage;
//        TextView productName, productCategory;
//        Button btnComment;
//        RatingBar productRating;
//
//        public ProductViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            productImage = itemView.findViewById(R.id.productImage);
//            productName = itemView.findViewById(R.id.productName);
//            productCategory = itemView.findViewById(R.id.productCategory);
//            btnComment = itemView.findViewById(R.id.btnComment);
//            productRating = itemView.findViewById(R.id.productRating);
//        }
//    }
//}
