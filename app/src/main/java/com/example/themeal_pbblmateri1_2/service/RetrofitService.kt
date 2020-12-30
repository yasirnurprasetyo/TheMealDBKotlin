package com.example.themeal_pbblmateri1_2.service
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.themeal_pbblmateri1_2.model.Meals
import com.example.themeal_pbblmateri1_2.model.MealDetails
interface RetrofitService {
    @GET("filter.php")
    fun getMealsByCategory(@Query("c") foodCategory: String): Deferred<Meals>
    @GET("lookup.php")
    fun getMealsById(@Query("i") foodId: String): Deferred<MealDetails>
    @GET("search.php")
    fun getSearchMeal(@Query("s") foodId: String): Deferred<Meals>
}