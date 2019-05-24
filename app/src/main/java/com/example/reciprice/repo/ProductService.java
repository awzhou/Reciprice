package com.example.reciprice.repo;

import com.example.reciprice.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {

    @GET("/prod/trial/lookup?")
    Call<ProductResponse> findByUpc(@Query("upc")String upc);
}
