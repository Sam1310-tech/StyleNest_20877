package com.example.stylenest_20877;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ProductlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        String category = getIntent().getStringExtra("category");
        if (category == null) category = "COLLECTIONS";

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Product> allProducts = new ArrayList<>();
        allProducts.add(new Product("Urban Slim Shirt", "KES 2,500", "SHIRTS"));
        allProducts.add(new Product("Classic Chinos", "KES 3,200", "TROUSERS"));
        allProducts.add(new Product("Denim Jacket", "KES 4,500", "SHIRTS"));
        allProducts.add(new Product("Cotton Polo", "KES 1,800", "SHIRTS"));
        allProducts.add(new Product("Cargo Pants", "KES 3,500", "TROUSERS"));
        allProducts.add(new Product("Leather Belt", "KES 1,200", "ACCESSORIES"));
        allProducts.add(new Product("Street Sneakers", "KES 5,800", "ACCESSORIES"));
        allProducts.add(new Product("Linen Dress", "KES 3,900", "SHIRTS"));
        allProducts.add(new Product("Silk Scarf", "KES 1,500", "ACCESSORIES"));
        allProducts.add(new Product("Graphic Tee", "KES 1,200", "SHIRTS"));
        allProducts.add(new Product("Wide Leg Trousers", "KES 3,400", "TROUSERS"));
        allProducts.add(new Product("Bucket Hat", "KES 1,000", "ACCESSORIES"));

        List<Product> filteredProducts = new ArrayList<>();
        if (category.equals("COLLECTIONS")) {
            filteredProducts = allProducts;
        } else {
            for (Product p : allProducts) {
                if (p.category.equalsIgnoreCase(category)) {
                    filteredProducts.add(p);
                }
            }
        }

        recyclerView.setAdapter(new ProductAdapter(filteredProducts));
    }

    public static class Product {
        public String name;
        public String price;
        public String category;
        public Product(String name, String price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }
    }

    static class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
        List<Product> products;
        ProductAdapter(List<Product> products) { this.products = products; }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Product product = products.get(position);
            holder.name.setText(product.name);
            holder.price.setText(product.price);
            holder.category.setText(product.category);
            holder.itemView.setOnClickListener(v -> {
                android.content.Intent intent = new android.content.Intent(v.getContext(), ProductDetailActivity.class);
                v.getContext().startActivity(intent);
            });
        }

        @Override
        public int getItemCount() { return products.size(); }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, price, category;
            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.productName);
                price = itemView.findViewById(R.id.productPrice);
                category = itemView.findViewById(R.id.txtCategoryTag);
            }
        }
    }
}