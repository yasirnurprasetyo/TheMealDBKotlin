package com.example.themeal_pbblmateri1_2.presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.themeal_pbblmateri1_2.service.RetrofitFactory
import com.example.themeal_pbblmateri1_2.view.GeneralView
import java.lang.Exception
class AllPresenter (private  val view: GeneralView){
    fun getMealsByCategory(category: String){
        view.showLoading()
        val api = RetrofitFactory.create()
        val request = api.getMealsByCategory(category)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e: Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }
    fun getMealById(mealId: String) {
        view.showLoading()
        val api = RetrofitFactory.create()
        val request =  api.getMealsById(mealId)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e: Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }
    fun getSearchMeal(mealId: String) {
        view.showLoading()
        val api = RetrofitFactory.create()
        val request = api.getSearchMeal(mealId)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e:Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }
}