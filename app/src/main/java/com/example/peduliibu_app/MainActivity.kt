package com.example.peduliibu_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peduliibu_app.Adapter.ViewPagerAdapter
import com.example.peduliibu_app.Fragment.NutritionFragment.NutritionFragment.NutritionFragment
import com.example.peduliibu_app.Fragment.NutritionFragment.OpeningNutritionFragment
import com.example.peduliibu_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OpeningNutritionFragment.OnMenuSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Your existing setupTab method or other initializations
    }

    override fun onMenuSelected(isRecommendationMenu: Boolean) {
        val fragment = if (isRecommendationMenu) {
            NutritionFragment()  // Navigate to NutritionFragment
        } else {
            NutritionFragment()  // Navigate to CustomNutritionFragment
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}