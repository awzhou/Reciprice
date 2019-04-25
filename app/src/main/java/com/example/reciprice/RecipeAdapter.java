package com.example.reciprice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{
    private List<Recipe> recipes;

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewRecipeImage;
        private TextView textViewRecipeTitle;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewRecipeImage= itemView.findViewById(R.id.imageView_recipe_item_image);
            textViewRecipeTitle= itemView.findViewById(R.id.textView_recipe_item_title);
        }
    }

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;

    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_item, viewGroup, false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(rootView);

        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int position) {
        Recipe currentRecipe = recipes.get(position);
        recipeViewHolder.textViewRecipeTitle.setText(currentRecipe.getLabel());
        Glide.with(recipeViewHolder.imageViewRecipeImage).load(currentRecipe.getImageURL()).into(recipeViewHolder.imageViewRecipeImage);
    }

    @Override
    public int getItemCount() {
        if(recipes == null){
            return 0;
        }else{
            return recipes.size();
        }
    }


}
