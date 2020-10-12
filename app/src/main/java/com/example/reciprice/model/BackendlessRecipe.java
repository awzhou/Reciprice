package com.example.reciprice.model;

public class BackendlessRecipe {
    private String label;
    private String recipeURL;
    private String dietLabels;
    private String healthLabels;
    private String imageURL;
    private String ingredientLines;
    private String cautions;
    private String objectId;
    private String ownerId;

    public BackendlessRecipe() {
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

    public String getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(String dietLabels) {
        this.dietLabels = dietLabels;
    }

    public String getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(String healthLabels) {
        this.healthLabels = healthLabels;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(String ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public String getCautions() {
        return cautions;
    }

    public void setCautions(String cautions) {
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
        return "BackendlessRecipe{" +
                "label='" + label + '\'' +
                ", recipeURL='" + recipeURL + '\'' +
                ", dietLabels='" + dietLabels + '\'' +
                ", healthLabels='" + healthLabels + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", ingredientLines='" + ingredientLines + '\'' +
                ", cautions=" + cautions +
                ", objectId='" + objectId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
