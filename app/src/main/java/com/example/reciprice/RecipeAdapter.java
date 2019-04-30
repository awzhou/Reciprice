package com.example.reciprice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{
    private List<Recipe> recipes;




    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewRecipeImage;
        private TextView textViewRecipeTitle;
        private TextView textViewRecipeCaution;


        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewRecipeImage = itemView.findViewById(R.id.imageView_recipe_item_image);
            textViewRecipeTitle = itemView.findViewById(R.id.textView_recipe_item_title);
            textViewRecipeCaution = itemView.findViewById(R.id.textView_recipe_item_caution);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    Recipe recipe =  recipes.get(pos);

                    Gson gson = new Gson();
                    String json = gson.toJson(recipe);

                    Intent intent = new Intent(v.getContext(), IngredientListActivity.class);
                    v.getContext().startActivity(intent);


                }
            });
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
        String cautions = "";
        for (String c : currentRecipe.getCautions()) {
            cautions += c + ", ";
        }
        if (cautions.length() > 0) {
            cautions = cautions.substring(0, cautions.length() - 2);
        }

        recipeViewHolder.textViewRecipeTitle.setText(currentRecipe.getLabel());
        recipeViewHolder.textViewRecipeCaution.setText(cautions);
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
