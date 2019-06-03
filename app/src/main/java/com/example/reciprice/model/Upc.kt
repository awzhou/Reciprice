package com.example.reciprice.model

data class Upc(
        val ingredientImage: String,
        val metaInformation: List<String>,
        val original: String,
        val originalName: String,
        val originalString: String,
        val products: List<Product>
)