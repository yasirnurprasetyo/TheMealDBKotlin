package com.example.themeal_pbblmateri1_2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_pbblmateri1_2.adapter.MealsAdapter
import com.example.themeal_pbblmateri1_2.model.MealResponse
import com.example.themeal_pbblmateri1_2.model.Meals
import com.example.themeal_pbblmateri1_2.presenter.AllPresenter
import com.example.themeal_pbblmateri1_2.view.GeneralView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GeneralView, MealsAdapter.Listener {
    private  lateinit var presenter: AllPresenter
    private lateinit var adapter : MealsAdapter
    private var listMeals : MutableList<MealResponse> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingRecycleViewMeals()
        loadMeals()
        loadSpinner()
        var searchView: SearchView = findViewById(R.id.search_view)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if(query.toString().length >= 3){
                    presenter.getSearchMeal(query.toString())
                }
                return true
            }

        })
    }

    private fun loadMeals(){
        presenter = AllPresenter(this)
        presenter.getMealsByCategory("Seafood")
    }
    private fun settingRecycleViewMeals(){
        rv_meal.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_meal.layoutManager = layoutManager
        adapter = MealsAdapter(listMeals, this)
        rv_meal.adapter = adapter
    }
    override fun showLoading(){
        main_pb.visibility = View.VISIBLE
        Log.d("Meal", "Start Loading")
    }
    override fun error(error: Throwable?){
        Toast.makeText(this, "Error Detected!", Toast.LENGTH_LONG).show()
        Log.d("Meal", "Error Detected!")
    }
    override fun success(response: Any) {
        val result = response as Meals
        listMeals.clear()
        listMeals.addAll(result.meals)
        adapter.notifyDataSetChanged()

    }
    override fun hideLoading() {
        main_pb.visibility = View.GONE
    }
    override fun onClick(meal: MealResponse) {
        val intent = Intent(this, MealDetailActivity::class.java)
            intent.putExtra("mealID", meal.idMeal)
        this.startActivity(intent)
    }

    private fun loadSpinner(){
        var category = resources.getStringArray(R.array.meals_category)
        var spinner: Spinner = findViewById(R.id.sp_category)
        if (spinner != null){
            val adapter = ArrayAdapter(this, R.layout.style_spinner, category)
            spinner.adapter = adapter
            android.R.layout.simple_spinner_item
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    presenter.getMealsByCategory(category[position])
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}