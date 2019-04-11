package com.example.basiclogins;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {

    // name, cuisine, rating, link, price, address
    private String name;
    private String cuisine;
    private double rating; // 0 --> 5 with 0.5 increments
    private int price; // 1 --> 5 with 1 increments
    private String address;

    //backendless fields
    private String objectId;
    private String ownerId;

    // need a default constructor for Backendless

    public Restaurant() {
    }

    public Restaurant(String name, String cuisine, String address, double rating, int price) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
        this.price = price;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", address='" + address + '\'' +
                '}';
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

    protected Restaurant(Parcel in) {
        name = in.readString();
        cuisine = in.readString();
        rating = in.readDouble();
        price = in.readInt();
        address = in.readString();
        objectId = in.readString();
        ownerId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(cuisine);
        dest.writeDouble(rating);
        dest.writeInt(price);
        dest.writeString(address);
        dest.writeString(objectId);
        dest.writeString(ownerId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}
