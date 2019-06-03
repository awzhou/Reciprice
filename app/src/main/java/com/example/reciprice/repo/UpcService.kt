package com.example.reciprice.repo

import com.example.reciprice.model.Upc
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UpcService {

    @Headers("X-RapidAPI-Host:spoonacular-recipe-food-nutrition-v1.p.rapidapi.com", "X-RapidAPI-Key:4eced43f80mshed00119a7188f99p1c9537jsn1d729dfb7bae", "Content-Type:application/json")
    @POST("food/ingredients/map")
    fun getUpc(@Body body: HashMap<String, Array<String>>): Call<List<Upc>>
}