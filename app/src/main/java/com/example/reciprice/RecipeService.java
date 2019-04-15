package com.example.reciprice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeService {

    @GET("search")

    Call<RecipeResponse> searchByKeyWord(
            @Query("q")String keyword,
            @Query("app_id")String app_ID,
            @Query("app_key")String app_ley);



}
