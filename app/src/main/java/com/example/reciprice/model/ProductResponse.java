package com.example.reciprice.model;

import java.util.List;

public class ProductResponse {
    private Items items;
    private List<Offer> offers;

    public ProductResponse(){
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "items=" + items +
                ", offers=" + offers +
                '}';
    }
}
