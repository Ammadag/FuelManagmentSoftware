package com.example.fuelmanagmentsoftware.activities.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.fuelmanagmentsoftware.activities.room.UserDB
import com.example.fuelmanagmentsoftware.databinding.ActivitySplashBinding

class SplashActvity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        UserDB.getInstance(this)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, sign_in::class.java))
            finish()
        },1000)
    }
}