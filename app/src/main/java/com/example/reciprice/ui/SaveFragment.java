package com.example.reciprice.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.reciprice.R;
import com.example.reciprice.model.BackendlessRecipe;
import com.example.reciprice.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> favoriteRecipes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_save, container, false);

        favoriteRecipes = new ArrayList<>();
        readSaved();

        recyclerView = rootView.findViewById(R.id.recyclerView_save);

        layoutManager = new LinearLayoutManager(getContext());
        recipeAdapter = new RecipeAdapter(favoriteRecipes, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);

        registerForContextMenu(recyclerView);

        return rootView;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete:
                List<BackendlessRecipe> backendlessRecipes = new ArrayList<>();
                for(Recipe recipe : favoriteRecipes){
                    BackendlessRecipe backendlessRecipe = new BackendlessRecipe();

                    backendlessRecipe.setLabel(recipe.getLabel());
                    backendlessRecipe.setImageURL(recipe.getImageURL());
                    backendlessRecipe.setRecipeURL(recipe.getRecipeURL());
                    backendlessRecipe.setObjectId(recipe.getObjectId());
                    backendlessRecipe.setOwnerId(recipe.getOwnerId());
                    backendlessRecipe.setCautions(recipe.getCautions().toString());
                    Log.e("ingredient lines", recipe.getIngredientLines().toString());
                    backendlessRecipe.setIngredientLines(recipe.getIngredientLines().toString());
                    backendlessRecipe.setHealthLabels(recipe.getHealthLabels().toString());
                    backendlessRecipe.setDietLabels(recipe.getDietLabels().toString());

                    backendlessRecipes.add(backendlessRecipe);
                }
                BackendlessRecipe backendlessRecipe = backendlessRecipes.get(recipeAdapter.getPosition());
                deleteRecipe(backendlessRecipe);
                break;
        }
        return true;
    }

    private void deleteRecipe(BackendlessRecipe recipe) {
        Backendless.Persistence.of(BackendlessRecipe.class).remove(recipe, new AsyncCallback<Long>() {
            @Override
            public void handleResponse(Long response) {
                Toast.makeText(getContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                readSaved();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("fault", fault.getMessage());
            }
        });
    }

    public void readSaved(){
        String ownerId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "ownerId = '" + ownerId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        Backendless.Data.of(BackendlessRecipe.class).find(queryBuilder, new AsyncCallback<List<BackendlessRecipe>>() {
            @Override
            public void handleResponse(List<BackendlessRecipe> response) {
                favoriteRecipes.clear();
                for(BackendlessRecipe backendlessRecipe : response){
                    String cautions = backendlessRecipe.getCautions();
                    cautions = cautions.replaceAll("\\[", "").replaceAll("\\]","");

                    String ingredientLines = backendlessRecipe.getIngredientLines();
                    ingredientLines = ingredientLines.replaceAll("\\[", "").replaceAll("\\]","");

                    String healthLabels = backendlessRecipe.getHealthLabels();
                    healthLabels = healthLabels.replaceAll("\\[", "").replaceAll("\\]","");

                    String dietLabels = backendlessRecipe.getDietLabels();
                    dietLabels = dietLabels.replaceAll("\\[", "").replaceAll("\\]","");

                    Recipe recipe = new Recipe();
                    recipe.setLabel(backendlessRecipe.getLabel());
                    recipe.setImageURL(backendlessRecipe.getImageURL());
                    recipe.setRecipeURL(backendlessRecipe.getRecipeURL());
                    recipe.setObjectId(backendlessRecipe.getObjectId());
                    recipe.setOwnerId(backendlessRecipe.getOwnerId());
                    recipe.setCautions(Arrays.asList(cautions.split(", ")));
                    recipe.setIngredientLines(Arrays.asList(ingredientLines.split(", ")));
                    recipe.setHealthLabels(Arrays.asList(healthLabels.split(", ")));
                    recipe.setDietLabels(Arrays.asList(dietLabels.split(", ")));

                    favoriteRecipes.add(recipe);
                }

                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("fault", fault.getMessage());
            }
        });
    }
}
