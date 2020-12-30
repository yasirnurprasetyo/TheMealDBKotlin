package com.example.themeal_pbblmateri1_2.model


import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("idMeal")
    var idMeal: String,
    @SerializedName("strMeal")
    var strMeal: String,
    @SerializedName("strMealThumb")
    var strMealThumb: String
)