package com.example.reciprice.model;

import java.util.List;

public class ProductResponse {
    private List<Items> items;

    public ProductResponse(){
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "items=" + items +
                '}';
    }
}
