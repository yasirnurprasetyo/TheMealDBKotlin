package com.example.themeal_pbblmateri1_2
import android.os.Bundle
import android.view.View
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.themeal_pbblmateri1_2.model.MealDetails
import com.example.themeal_pbblmateri1_2.presenter.AllPresenter
import com.example.themeal_pbblmateri1_2.view.GeneralView
import kotlinx.android.synthetic.main.activity_meal_detail.*
import kotlinx.android.synthetic.main.content_scrolling.*
import java.lang.StringBuilder

class MealDetailActivity : AppCompatActivity(), GeneralView {
    private lateinit var presenter: AllPresenter
    private var mealID: String? = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        mealID = intent.getStringExtra("mealID")
        callAPI()
    }
    private fun callAPI(){
        presenter = AllPresenter(this)
        presenter.getMealById(mealID.toString())
    }
    override fun showLoading() {
        pb_detail.visibility = View.VISIBLE
    }
    override fun success(response: Any) {
        val result = response as MealDetails
        val meal = result.meals[0]
        toolbar_layout.title = meal.strMeal
        tv_insruction.text = meal.strInstructions
        Glide.with(this).load(meal.strMealThumb).into(backdrop)
        var ingredient: MutableList<String?> = arrayListOf(
            meal.strIngredient1,
            meal.strIngredient2,
            meal.strIngredient3,
            meal.strIngredient4,
            meal.strIngredient5,
            meal.strIngredient6,
            meal.strIngredient7,
            meal.strIngredient8,
            meal.strIngredient9,
            meal.strIngredient10,
            meal.strIngredient11,
            meal.strIngredient12,
            meal.strIngredient13,
            meal.strIngredient14,
            meal.strIngredient15,
            meal.strIngredient16,
            meal.strIngredient17,
            meal.strIngredient18,
            meal.strIngredient19,
            meal.strIngredient20)
        ingredient = ingredient.filterNotNull().toMutableList()
        val builder = StringBuilder()
        for (value in ingredient){
            tv_ingredients.text = builder.append("$value, ")
        }
    }
    override fun hideLoading() {
        pb_detail.visibility = View.GONE
    }
    override fun error(error: Throwable?) {
    }
}