package com.example.peduliibu_app.Authenticate.Opening

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.peduliibu_app.MainActivity
import com.example.peduliibu_app.R
import com.example.peduliibu_app.databinding.ActivityStatusBinding

class StatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatusBinding
    private var selectedStatus: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.rencana.setOnClickListener {
            selectedStatus = "rencana_kehamilan"
            updateCardViewSelection(binding.rencana, binding.hamil, binding.anak)
            navigateToHomePage()
        }

        binding.hamil.setOnClickListener {
            selectedStatus = "sedang_hamil"
            updateCardViewSelection(binding.hamil, binding.rencana, binding.anak)
            navigateToHomePage()
        }

        binding.anak.setOnClickListener {
            selectedStatus = "memiliki_anak"
            updateCardViewSelection(binding.anak, binding.rencana, binding.hamil)
            navigateToHomePage()
        }

        binding.hamil.setOnClickListener {
            when (selectedStatus) {
                "rencana_kehamilan", "sedang_hamil", "memiliki_anak" -> {
                    saveUserStatus(selectedStatus!!)
                    navigateToHomePage()
                }
                else -> {
                    showErrorMessage("Silakan pilih status Anda")
                }
            }
        }
    }

    private fun updateCardViewSelection(selectedCard: View, vararg unselectedCards: View) {
        selectedCard.setBackgroundResource(R.drawable.selected_card_background)
        unselectedCards.forEach { it.setBackgroundResource(R.drawable.unselected_card_background) }
    }

    private fun saveUserStatus(status: String) {
        getSharedPreferences("user_prefs", MODE_PRIVATE).edit().apply {
            putString("user_status", status)
            apply()
        }
    }

    private fun navigateToHomePage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Optional: finish this activity so user can't go back to status selection
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}