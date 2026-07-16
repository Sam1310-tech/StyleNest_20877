package com.example.stylenest_20877;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
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
        allProducts.add(new Product("Urban Slim Shirt", "KES 2,500", "SHIRTS", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=500&q=80"));
        allProducts.add(new Product("Classic Chinos", "KES 3,200", "TROUSERS", "https://images.unsplash.com/photo-1473966968600-fa801b869a1a?w=500&q=80"));
        allProducts.add(new Product("Denim Jacket", "KES 4,500", "SHIRTS", "https://images.unsplash.com/photo-1576995853123-5a10305d93c0?w=500&q=80"));
        allProducts.add(new Product("Cotton Polo", "KES 1,800", "SHIRTS", "https://images.unsplash.com/photo-1581655353564-df123a1eb820?w=500&q=80"));
        allProducts.add(new Product("Cargo Pants", "KES 3,500", "TROUSERS", "https://images.unsplash.com/photo-1517441167990-b8956bc2202a?w=500&q=80"));
        allProducts.add(new Product("Leather Belt", "KES 1,200", "ACCESSORIES", "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=500&q=80"));
        allProducts.add(new Product("Street Sneakers", "KES 5,800", "ACCESSORIES", "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=500&q=80"));
        allProducts.add(new Product("Linen Dress", "KES 3,900", "SHIRTS", "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=500&q=80"));
        allProducts.add(new Product("Silk Scarf", "KES 1,500", "ACCESSORIES", "https://images.unsplash.com/photo-1584917865442-de89df76afd3?w=500&q=80"));
        allProducts.add(new Product("Graphic Tee", "KES 1,200", "SHIRTS", "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=500&q=80"));
        allProducts.add(new Product("Wide Leg Trousers", "KES 3,400", "TROUSERS", "https://images.unsplash.com/photo-1594633312681-425c7b97ccd1?w=500&q=80"));
        allProducts.add(new Product("Bucket Hat", "KES 1,000", "ACCESSORIES", "https://images.unsplash.com/photo-1589831377283-33cb1cc6bd5d?w=500&q=80"));

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
        public String imageUrl;
        public Product(String name, String price, String category, String imageUrl) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.imageUrl = imageUrl;
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

            Glide.with(holder.itemView.getContext())
                .load(product.imageUrl)
                .placeholder(R.color.gray_light)
                .centerCrop()
                .into(holder.image);

            holder.itemView.setOnClickListener(v -> {
                android.content.Intent intent = new android.content.Intent(v.getContext(), ProductDetailActivity.class);
                v.getContext().startActivity(intent);
            });
        }

        @Override
        public int getItemCount() { return products.size(); }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, price, category;
            ImageView image;
            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.productName);
                price = itemView.findViewById(R.id.productPrice);
                category = itemView.findViewById(R.id.txtCategoryTag);
                image = itemView.findViewById(R.id.imgProduct);
            }
        }
    }
}