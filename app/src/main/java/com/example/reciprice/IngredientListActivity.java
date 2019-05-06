package com.example.reciprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class IngredientListActivity extends AppCompatActivity {
    private Recipe recipe;
    private TextView textViewTitle;
    private TextView textViewIngredients;
    private TextView textViewCautions;
    private TextView textViewDietLabels;
    private TextView textViewHealthLabels;
    private TextView textViewRecipeLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        Gson gson = new Gson();
        recipe = gson.fromJson(getIntent().getStringExtra("recipe"), Recipe.class);
        Intent intent = getIntent();

        wireWidgets();

        displayIngredients();
        displayCautions();
        displayDietLabels();
        displayHealthLabels();
        displayURL();

    }

    private void wireWidgets() {
        textViewTitle = findViewById(R.id.textView_ingredientList_recipe);
        textViewIngredients = findViewById(R.id.textView_ingredientList);
        textViewCautions = findViewById(R.id.textView_ingredientList_cautions);
        textViewDietLabels = findViewById(R.id.textView_ingredientList_dietLabels);
        textViewHealthLabels = findViewById(R.id.textView_ingredientList_healthLabels);
        textViewRecipeLink = findViewById(R.id.textView_ingredientList_recipeLink);
    }

    private void displayIngredients() {

        textViewTitle.setText(recipe.getLabel());

        //Populate Ingredients
        String unformattedIngredients = recipe.getIngredientLines().toString();
        String formattedIngredients = "";
        int startIndex = 1;
        int endIndex = unformattedIngredients.indexOf(",");
        ;

        while (endIndex > -1) {
            formattedIngredients += "• " + unformattedIngredients.substring(startIndex, endIndex) + "\n";
            unformattedIngredients = unformattedIngredients.substring(endIndex + 2);
            startIndex = 0;
            endIndex = unformattedIngredients.indexOf(",");
        }
        endIndex = unformattedIngredients.length() - 1;
        formattedIngredients += "• " + unformattedIngredients.substring(startIndex, endIndex);

        textViewIngredients.setText(formattedIngredients);

        if (unformattedIngredients == "[]") {
            textViewIngredients.setText("No ingredients found");
        }
    }

    private void displayCautions() {
        //Populate Cautions
        String unformattedCautions = recipe.getCautions().toString();
        String formattedCautions = "";
        int startIndex = 1;
        int endIndex = unformattedCautions.indexOf(",");

        while (endIndex > -1) {
            formattedCautions += "• " + unformattedCautions.substring(startIndex, endIndex) + "\n";
            unformattedCautions = unformattedCautions.substring(endIndex + 2);
            startIndex = 0;
            endIndex = unformattedCautions.indexOf(",");
        }
        endIndex = unformattedCautions.length() - 1;
        formattedCautions += "• " + unformattedCautions.substring(startIndex, endIndex);

        textViewCautions.setText(formattedCautions);

        if (unformattedCautions == "[]") {
            textViewCautions.setText("No cautions found");
        }
    }

    private void displayDietLabels() {
        //Populate Diet Labels
        String unformattedDietLabels = recipe.getDietLabels().toString();
        String formattedDietLabels = "";
        int startIndex = 1;
        int endIndex = unformattedDietLabels.indexOf(",");

        while (endIndex > -1) {
            formattedDietLabels += "• " + unformattedDietLabels.substring(startIndex, endIndex) + "\n";
            unformattedDietLabels = unformattedDietLabels.substring(endIndex + 2);
            startIndex = 0;
            endIndex = unformattedDietLabels.indexOf(",");
        }
        endIndex = unformattedDietLabels.length() - 1;
        formattedDietLabels += "• " + unformattedDietLabels.substring(startIndex, endIndex);

        textViewDietLabels.setText(formattedDietLabels);

        if (unformattedDietLabels == "[]") {
            textViewDietLabels.setText("No diet labels found");
        }
    }

    private void displayHealthLabels() {
        //Populate Health Labels
        String unformattedHealthLabels = recipe.getHealthLabels().toString();
        String formattedHealthLabels = "";
        int startIndex = 1;
        int endIndex = unformattedHealthLabels.indexOf(",");

        while (endIndex > -1) {
            formattedHealthLabels += "• " + unformattedHealthLabels.substring(startIndex, endIndex) + "\n";
            unformattedHealthLabels = unformattedHealthLabels.substring(endIndex + 2);
            startIndex = 0;
            endIndex = unformattedHealthLabels.indexOf(",");
        }
        endIndex = unformattedHealthLabels.length() - 1;
        formattedHealthLabels += "• " + unformattedHealthLabels.substring(startIndex, endIndex);
        unformattedHealthLabels = unformattedHealthLabels.substring(endIndex);

        textViewHealthLabels.setText(formattedHealthLabels);

        if (unformattedHealthLabels == "[]") {
            textViewHealthLabels.setText("No health labels found");
        }
    }

    private void displayURL() {
        //Populate URL
        textViewRecipeLink.setText(recipe.getRecipeURL().toString());

        if (recipe.getRecipeURL().toString() == "[]") {
            textViewIngredients.setText("No URL found");
        }
    }
}

