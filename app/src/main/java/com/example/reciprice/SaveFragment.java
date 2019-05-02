package com.example.reciprice;

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
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SaveFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView textViewTitle;
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
        textViewTitle = rootView.findViewById(R.id.textView_save_title);

        layoutManager = new LinearLayoutManager(getContext());
        recipeAdapter = new RecipeAdapter(favoriteRecipes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);

        return rootView;
    }

    public void readSaved(){
        String ownerId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "ownerId = '" + ownerId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        Backendless.Data.of(Recipe.class).find(queryBuilder, new AsyncCallback<List<Recipe>>() {
            @Override
            public void handleResponse(List<Recipe> response) {
                favoriteRecipes.clear();
                favoriteRecipes.addAll(response);
                Log.e("favoriteRecipes", favoriteRecipes.toString() + "");
                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("fault", fault.getMessage());
            }
        });
    }
}
