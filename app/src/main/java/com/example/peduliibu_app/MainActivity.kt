package com.example.peduliibu_app

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peduliibu_app.Adapter.ViewPagerAdapter
import com.example.peduliibu_app.Fragment.HomeFragment
import com.example.peduliibu_app.Fragment.NutritionFragment.NutritionFragment.NutritionFragment
import com.example.peduliibu_app.Fragment.NutritionFragment.OpeningNutritionFragment
import com.example.peduliibu_app.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.peduliibu_app.Fragment.ProfileFragment.ProfileFragment

class MainActivity : AppCompatActivity(), OpeningNutritionFragment.OnMenuSelectedListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up NavController untuk NutritionFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        // Set HomeFragment sebagai default
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                findViewById<FrameLayout>(R.id.fragment_container).visibility = View.VISIBLE
                findViewById<FragmentContainerView>(R.id.nav_host_fragment).visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commit()
            }
            R.id.nav_nutrition -> {
                findViewById<FrameLayout>(R.id.fragment_container).visibility = View.GONE
                findViewById<FragmentContainerView>(R.id.nav_host_fragment).visibility = View.VISIBLE
                navController.navigate(R.id.openingNutritionFragment)
            }
            R.id.nav_profile -> {
                findViewById<FrameLayout>(R.id.fragment_container).visibility = View.VISIBLE
                findViewById<FragmentContainerView>(R.id.nav_host_fragment).visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment())
                    .commit()
            }
        }
        true
    }

    override fun onMenuSelected(isRecommendationMenu: Boolean) {
        val action = if (isRecommendationMenu) {
            R.id.action_openingNutritionFragment_to_nutritionFragment
        } else {
            R.id.action_openingNutritionFragment_to_nutritionFragment
        }
        navController.navigate(action)
    }
}