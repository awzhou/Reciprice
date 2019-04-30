package com.example.reciprice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SaveFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView textViewTitle;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeAdapter recipeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_save, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView_save);
        textViewTitle = rootView.findViewById(R.id.textView_save_title);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);

        return rootView;
    }
}
