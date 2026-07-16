package com.example.stylenest_20877;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadCategoryImages();

        // Bind the "SHOP NOW" element button
        Button btnExplore = findViewById(R.id.btnExplore);

        // Setup simple transition command behavior to the next activity screen
        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductlistActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.cardShirts).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductlistActivity.class);
            intent.putExtra("category", "SHIRTS");
            startActivity(intent);
        });

        findViewById(R.id.cardTrousers).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductlistActivity.class);
            intent.putExtra("category", "TROUSERS");
            startActivity(intent);
        });

        findViewById(R.id.cardAccessories).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductlistActivity.class);
            intent.putExtra("category", "ACCESSORIES");
            startActivity(intent);
        });

        setupFeaturedProducts();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_home);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_categories) {
                startActivity(new Intent(MainActivity.this, ProductlistActivity.class));
                return true;
            } else if (id == R.id.nav_cart) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                return true;
            }
            return true;
        });
    }

    private void loadCategoryImages() {
        Glide.with(this).load("https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=800&q=80").into((android.widget.ImageView) findViewById(R.id.imgBanner));
        Glide.with(this).load("https://images.unsplash.com/photo-1620799140408-edc6dcb6d633?w=500&q=80").into((android.widget.ImageView) findViewById(R.id.imgCategoryShirts));
        Glide.with(this).load("https://images.unsplash.com/photo-1542272604-787c3835535d?w=500&q=80").into((android.widget.ImageView) findViewById(R.id.imgCategoryTrousers));
        Glide.with(this).load("https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500&q=80").into((android.widget.ImageView) findViewById(R.id.imgCategoryAccessories));
    }

    private void setupFeaturedProducts() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFeatured);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<ProductlistActivity.Product> featured = new ArrayList<>();
        featured.add(new ProductlistActivity.Product("Urban Slim Shirt", "KES 2,500", "SHIRTS", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=500&q=80"));
        featured.add(new ProductlistActivity.Product("Classic Chinos", "KES 3,200", "TROUSERS", "https://images.unsplash.com/photo-1473966968600-fa801b869a1a?w=500&q=80"));
        featured.add(new ProductlistActivity.Product("Denim Jacket", "KES 4,500", "SHIRTS", "https://images.unsplash.com/photo-1576995853123-5a10305d93c0?w=500&q=80"));
        featured.add(new ProductlistActivity.Product("Cotton Polo", "KES 1,800", "SHIRTS", "https://images.unsplash.com/photo-1581655353564-df123a1eb820?w=500&q=80"));

        recyclerView.setAdapter(new ProductlistActivity.ProductAdapter(featured));
    }
}