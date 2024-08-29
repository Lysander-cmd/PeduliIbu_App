package com.example.peduliibu_app.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peduliibu_app.Model.FoodData
import com.example.peduliibu_app.R

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var foodList: List<FoodData> = listOf()

    fun setFoodList(list: List<FoodData>) {
        foodList = list
        Log.d("FoodAdapter", "List size: ${foodList.size}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_menu_rekomendasi_card, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.bind(foodItem)
    }

    override fun getItemCount(): Int = foodList.size

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.tvCategory)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription)
        private val caloriesTextView: TextView = itemView.findViewById(R.id.tvCalories)
        private val foodImageView: ImageView = itemView.findViewById(R.id.ivFood)

        fun bind(foodItem: FoodData) {
            categoryTextView.text = foodItem.category
            descriptionTextView.text = foodItem.description
            caloriesTextView.text = "${foodItem.calories} kcal"

            Glide.with(itemView.context)
                .load(foodItem.imageUrl)
                .into(foodImageView)
        }
    }
}