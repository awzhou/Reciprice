package com.example.reciprice;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipes;

    private EditText searchText;
    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);

        wireWidgets(rootView);

        recipes = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        recipeAdapter = new RecipeAdapter(recipes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipes.clear();
                searchRecipes();
                // TODO: Hide keyboard
            }
        });

        return rootView;
    }

    private void wireWidgets(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerView_display);
        searchText = rootView.findViewById(R.id.editText_display_search);
        searchButton = rootView.findViewById(R.id.button_display_search);
    }

    private void searchRecipes() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.edamam.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeService service = retrofit.create(RecipeService.class);
        Call<RecipeResponse> recipeResponseCall = service.searchByKeyWord(searchText.getText().toString(), "255f9b26", "94b4e1023e6be9907d1210dfdcbfa935");

        recipeResponseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.body().getHits() != null) {
                    List<RecipeWrapper> recipeWrappers = response.body().getHits();

                    List<Recipe> newRecipes = addRecipes(recipeWrappers);
                    Log.e("recipes", newRecipes + "");

                    recipes.addAll(newRecipes);
                    recipeAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "No recipes were found. Please enter another search.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.d("ENQUEUE", "onFailure: " + t.getMessage());
            }
        });

    }

    private List<Recipe> addRecipes(List<RecipeWrapper> recipeWrappers) {
        List<Recipe> newRecipes = new ArrayList<>();
        for(RecipeWrapper recipeWrapper : recipeWrappers){
            newRecipes.add(recipeWrapper.getRecipe());
        }
        return newRecipes;
    }
}