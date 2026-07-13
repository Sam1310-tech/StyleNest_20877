package com.example.stylenest_20877;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}