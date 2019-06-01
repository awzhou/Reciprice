package com.example.reciprice.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.reciprice.R;
import com.example.reciprice.model.Items;
import com.example.reciprice.model.Offer;
import com.example.reciprice.model.ProductResponse;
import com.example.reciprice.repo.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientActivity extends AppCompatActivity {


    private String ingredient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        ingredient = getIntent().getStringExtra("Ingredient");
        Intent intent = getIntent();

        Button button = findViewById(R.id.yah);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngredientActivity.this, PriceActivity.class);
                intent.putExtra("upc", "039978003157");
                startActivity(intent);

            }
        });
    }


}
