package com.example.themeal_pbblmateri1_2.model


import com.google.gson.annotations.SerializedName

data class MealDetails(
    @SerializedName("meals")
    var meals: List<MealDetailItem>
)