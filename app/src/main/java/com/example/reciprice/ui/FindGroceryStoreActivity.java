package com.example.reciprice.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.reciprice.R;
import com.example.reciprice.model.Recipe;
import java.util.ArrayList;
import java.util.List;

public class FindGroceryStoreActivity extends AppCompatActivity implements FindGroceryStoreAdapter.ItemClickListener{

    private List<String> ingredientList;
    private FindGroceryStoreAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_grocery_store);

        ingredientList = getIntent().getStringArrayListExtra("ingredientList");
        Intent intent = getIntent();


        RecyclerView recyclerView = findViewById(R.id.recyclerView_ingredients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FindGroceryStoreAdapter(this, ingredientList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(FindGroceryStoreActivity.this, IngredientActivity.class);
        intent.putExtra("Ingredient", adapter.getItem(position));
        startActivity(intent);
        //TODO: search this clicked ingredient in the grocery store service
    }
}
