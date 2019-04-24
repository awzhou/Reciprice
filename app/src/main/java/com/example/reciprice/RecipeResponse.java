package com.example.reciprice;

import java.util.List;

public class RecipeResponse {

    private List<Recipe> hits;


    public String toString() {
        return "RecipeResponse{" +
                "hits=" + hits +
                '}';
    }

    public RecipeResponse(List<Recipe> hits) {
        this.hits = hits;
    }

    public List<Recipe> getHits() {

        return hits;
    }

    public void setHits(List<Recipe> hits) {
        this.hits = hits;
    }


}
