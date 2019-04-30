package com.example.reciprice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        readFile();

        recyclerView = rootView.findViewById(R.id.recyclerView_save);
        textViewTitle = rootView.findViewById(R.id.textView_save_title);

        layoutManager = new LinearLayoutManager(getContext());
        recipeAdapter = new RecipeAdapter(favoriteRecipes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);

        return rootView;
    }

    public void readFile(){
        try {
            FileInputStream fileInputStream = getContext().openFileInput("favoriteRecipes.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            try {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String json = stringBuilder.toString();
            Gson gson = new Gson();
            favoriteRecipes = gson.fromJson(json, new TypeToken<ArrayList<Recipe>>() {
            }.getType());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
