package com.example.reciprice.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.reciprice.Credentials;
import com.example.reciprice.R;
import com.example.reciprice.model.BackendlessRecipe;
import com.example.reciprice.model.Recipe;
import com.example.reciprice.model.RecipeResponse;
import com.example.reciprice.model.RecipeWrapper;
import com.example.reciprice.repo.RecipeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class DisplayFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipes;
    private ProgressBar progressBar;

    private EditText searchText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);

        wireWidgets(rootView);

        recipes = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        recipeAdapter = new RecipeAdapter(recipes, true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);

        if(isLoggedIn()){
            registerForContextMenu(recyclerView);
        }

        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    recipes.clear();
                    searchRecipes();
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    private void wireWidgets(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerView_display);
        searchText = rootView.findViewById(R.id.editText_display_search);
        progressBar = rootView.findViewById(R.id.progressbar_display_loading);
    }

    private void searchRecipes() {

        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.edamam.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeService service = retrofit.create(RecipeService.class);
        Call<RecipeResponse> recipeResponseCall = service.searchByKeyWord(searchText.getText().toString(), Credentials.EDAMAM_APP_ID, Credentials.EDAMAM_API_KEY);

        recipeResponseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (!response.body().getHits().isEmpty()) {
                    List<RecipeWrapper> recipeWrappers = response.body().getHits();

                    List<Recipe> newRecipes = addRecipes(recipeWrappers);

                    recipes.addAll(newRecipes);
                    recipeAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(getContext(), "No recipes were found. Please enter another search.", Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Gson gson = new GsonBuilder().create();
        switch(item.getItemId()){
            case R.id.save:
                Recipe recipe = recipes.get(recipeAdapter.getPosition());

                Log.e("recipe", recipes.get(recipeAdapter.getPosition()) + "");

                BackendlessRecipe backendlessRecipe = new BackendlessRecipe();
                backendlessRecipe.setLabel(recipe.getLabel());
                backendlessRecipe.setImageURL(recipe.getImageURL());
                backendlessRecipe.setRecipeURL(recipe.getRecipeURL());
                backendlessRecipe.setObjectId(recipe.getObjectId());
                backendlessRecipe.setOwnerId(recipe.getOwnerId());
                backendlessRecipe.setCautions(recipe.getCautions().toString());
                backendlessRecipe.setIngredientLines(recipe.getIngredientLines().toString());
                backendlessRecipe.setHealthLabels(recipe.getHealthLabels().toString());
                backendlessRecipe.setDietLabels(recipe.getDietLabels().toString());


                saveRecipe(backendlessRecipe);
                break;
        }
        return true;
    }

    public void saveRecipe(BackendlessRecipe backendlessRecipe) {
        Backendless.Persistence.save(backendlessRecipe, new AsyncCallback<BackendlessRecipe>() {
            @Override
            public void handleResponse(BackendlessRecipe response) {
                Toast.makeText(getContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("fault", fault.getMessage() + "");
            }
        });
    }

    public boolean isLoggedIn() {
        if(Backendless.UserService.CurrentUser() != null){
            return true;
        }
        return  false;
    }
}