package com.example.reciprice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class DisplayFragment extends Fragment {

    private EditText searchText;
    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        wireWidgets(rootView);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchRecipes();
            }
        });

        return rootView;

    }

    private void wireWidgets(View rootView) {

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
                     List<Recipe> recipes = response.body().getHits();
                     Log.d("ENQUEUE", "onResponse: " + recipes.toString());
                }

                // call the recycler View of recipes
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.d("ENQUEUE", "onFailure: " + t.getMessage());
            }
        });

    }
}