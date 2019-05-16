package com.example.reciprice.model;

public class RecipeWrapper {
    private Recipe recipe;

    public RecipeWrapper() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "RecipeWrapper{" +
                "recipe=" + recipe +
                '}';
    }
}
