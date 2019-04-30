package com.example.reciprice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

public class IngredientListActivity extends AppCompatActivity {
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        Gson gson = new Gson();
        recipe = gson.fromJson(getIntent().getStringExtra("recipe"), Recipe.class);
        Intent intent = getIntent();

    }
}
