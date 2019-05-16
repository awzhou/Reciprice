package com.example.reciprice.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.reciprice.R;
import com.example.reciprice.model.Recipe;

import java.util.ArrayList;

public class findGroceryStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_grocery_store);

        ArrayList<String> ingredientList = getIntent().getStringArrayListExtra("ingredientList");
        Intent intent = getIntent();
    }
}
