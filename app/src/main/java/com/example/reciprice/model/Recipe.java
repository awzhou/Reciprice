package com.example.reciprice.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {

    private String label;
    @SerializedName("url")
    private String recipeURL;
    private List<String> dietLabels;
    private List<String> healthLabels;
    @SerializedName("image")
    private String imageURL;
    private List<String> ingredientLines;
    private List<String> cautions;
    private String objectId;
    private String ownerId;

    public Recipe() {
    }

    public Recipe(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRecipeURL() {
        return recipeURL;
    }

    public void setRecipeURL(String recipeURL) {
        this.recipeURL = recipeURL;
    }

    public List<String> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(List<String> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(List<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public List<String> getCautions() {
        return cautions;
    }

    public void setCautions(List<String> cautions) {
        this.cautions = cautions;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "label='" + label + '\'' +
                ", recipeURL='" + recipeURL + '\'' +
                ", dietLabels=" + dietLabels +
                ", healthLabels=" + healthLabels +
                ", imageURL='" + imageURL + '\'' +
                ", ingredientLines=" + ingredientLines +
                ", cautions=" + cautions +
                ", objectId='" + objectId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
