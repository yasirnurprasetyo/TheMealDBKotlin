package com.example.themeal_pbblmateri1_2.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themeal_pbblmateri1_2.R
import com.example.themeal_pbblmateri1_2.model.MealResponse
class MealsAdapter(
    private val meals: MutableList<MealResponse>,
    private val listener: Listener
) : RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {
    interface Listener{
        fun onClick(meal : MealResponse)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.example.themeal_pbblmateri1_2.R.layout.item_meal, parent, false)
        return MealsViewHolder(view)
    }
    override fun getItemCount(): Int {
        return meals.size
    }
    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.bindModel(meals[position], listener)
    }
    inner class MealsViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val tvMealName : TextView = itemView.findViewById(R.id.tv_meal_name)
        val imgMeal : ImageView = itemView.findViewById(R.id.img_meal)
        fun bindModel(meal: MealResponse, listener: Listener){
            tvMealName.text = meal.strMeal
            Glide.with(itemView).load(meal.strMealThumb).into(imgMeal)
            itemView.setOnClickListener{
                listener.onClick(meal)
            }
        }
    }
}