package com.example.reciprice.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.reciprice.R;
import com.example.reciprice.model.Items;
import com.example.reciprice.model.Offer;
import com.example.reciprice.model.ProductResponse;
import com.example.reciprice.model.Recipe;
import com.example.reciprice.repo.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindGroceryStoreActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_grocery_store);

        ArrayList<String> ingredientList = getIntent().getStringArrayListExtra("ingredientList");
        Intent intent = getIntent();
    }

    private void searchPrices(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.upcitemdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService service = retrofit.create(ProductService.class);
        Call<ProductResponse> productServiceCall = service.findByUpc(upc);

        productServiceCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Items information = response.body().getItems();
                List<Offer> offers = response.body().getOffers();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e("ENQUEUE", "onFailure: " + t.getMessage());
            }
        });
    }
}
