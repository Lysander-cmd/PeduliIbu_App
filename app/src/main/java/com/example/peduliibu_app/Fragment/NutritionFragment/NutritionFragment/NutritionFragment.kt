package com.example.peduliibu_app.Fragment.NutritionFragment.NutritionFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peduliibu_app.Adapter.FoodAdapter
import com.example.peduliibu_app.Model.FoodData
import com.example.peduliibu_app.R
import com.example.peduliibu_app.databinding.FragmentNutritionBinding
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

class NutritionFragment : Fragment() {
    private lateinit var binding: FragmentNutritionBinding
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupFirebase()
        loadFoodData()
    }

    private fun setupRecyclerView() {
        foodAdapter = FoodAdapter()
        binding.makanan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = foodAdapter
        }
    }

    private fun setupFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance()
    }

    private fun loadFoodData() {
        val currentDay = getCurrentDay()
        binding.tvDay.text = currentDay

        val categories = listOf("Sarapan", "Snack Pagi", "Makan Siang", "Snack Sore", "Makan Malam")
        val foodList = mutableListOf<FoodData>()

        firebaseDatabase.reference.child("makanan").get().addOnSuccessListener { snapshot ->
            snapshot.children.forEach { categorySnapshot ->
                val category = categorySnapshot.key ?: ""
                categorySnapshot.children.forEach { foodSnapshot ->
                    val description = foodSnapshot.child("deskripsi").getValue(String::class.java) ?: ""
                    val imageUrl = foodSnapshot.child("image").getValue(String::class.java) ?: ""
                    val calories = foodSnapshot.child("kalori").getValue(String::class.java)?.replace(" kcal", "")?.toIntOrNull() ?: 0
                    val categoryFromData = foodSnapshot.child("kategori").getValue(String::class.java) ?: category

                    foodList.add(FoodData(description, imageUrl, calories, categoryFromData))
                }
            }

            val randomizedMenu = categories.mapNotNull { category ->
                foodList.filter { it.category == category }.randomOrNull()
            }

            foodAdapter.setFoodList(randomizedMenu)
        }
    }

    private fun getCurrentDay(): String {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return when (dayOfWeek) {
            Calendar.MONDAY -> "Senin"
            Calendar.TUESDAY -> "Selasa"
            Calendar.WEDNESDAY -> "Rabu"
            Calendar.THURSDAY -> "Kamis"
            Calendar.FRIDAY -> "Jumat"
            Calendar.SATURDAY -> "Sabtu"
            Calendar.SUNDAY -> "Minggu"
            else -> "Unknown"
        }
    }
}