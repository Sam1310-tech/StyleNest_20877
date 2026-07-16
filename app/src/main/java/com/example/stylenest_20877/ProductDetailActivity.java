package com.example.stylenest_20877;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        String name = getIntent().getStringExtra("product_name");
        String price = getIntent().getStringExtra("product_price");
        String imageUrl = getIntent().getStringExtra("product_image");

        if (name != null) {
            ((TextView) findViewById(R.id.detailProductName)).setText(name);
        }
        if (price != null) {
            ((TextView) findViewById(R.id.detailProductPrice)).setText(price);
        }
        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into((ImageView) findViewById(R.id.imgDetailProduct));
        }
    }
}