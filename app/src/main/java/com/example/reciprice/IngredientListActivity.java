package com.example.reciprice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientListActivity extends AppCompatActivity {
    private Recipe recipe;
    private TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        Gson gson = new Gson();
        recipe = gson.fromJson(getIntent().getStringExtra("recipe"), Recipe.class);
        Intent intent = getIntent();

        wireWidgets();

        displayIngredients();

    }

    private void wireWidgets() {

        ingredients = findViewById(R.id.textView_ingredientList);
    }

    private void displayIngredients() {


        ingredients.setText(
                        recipe.getIngredientLines().toString() +  "\n" +
                        recipe.getCautions().toString() + "\n" +
                        recipe.getDietLabels().toString() + "\n" +
                        recipe.getHealthLabels().toString() +"\n" +
                        recipe.getRecipeURL().toString());






    }
}

