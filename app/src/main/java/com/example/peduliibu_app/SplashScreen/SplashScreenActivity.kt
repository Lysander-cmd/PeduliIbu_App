package com.example.peduliibu_app.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peduliibu_app.Authenticate.Login.LoginActivity
import com.example.peduliibu_app.Authenticate.Opening.StatusActivity
import com.example.peduliibu_app.Authenticate.SessionManager
import com.example.peduliibu_app.MainActivity
import com.example.peduliibu_app.R

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sessionManager = SessionManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (sessionManager.isLoggedIn()) {
                if (sessionManager.isFirstLogin()) {
                    startActivity(Intent(this, StatusActivity::class.java))
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000)
    }
    }