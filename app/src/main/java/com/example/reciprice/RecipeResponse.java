package com.example.reciprice;

import java.util.List;

public class RecipeResponse {

    private List<RecipeWrapper> hits;


    public String toString() {
        return "RecipeResponse{" +
                "hits=" + hits +
                '}';
    }

    public RecipeResponse() {
    }

    public List<RecipeWrapper> getHits() {

        return hits;
    }

    public void setHits(List<RecipeWrapper> hits) {
        this.hits = hits;
    }


}
