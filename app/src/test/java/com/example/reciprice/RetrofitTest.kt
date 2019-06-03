package com.example.reciprice

import com.example.reciprice.repo.UpcService
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {
    @Test
    fun testUpc() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(UpcService::class.java)
        val response = service.getUpc(hashMapOf("ingredients" to arrayOf("4 large eggs"))).execute()
        println(response.body())
    }
}