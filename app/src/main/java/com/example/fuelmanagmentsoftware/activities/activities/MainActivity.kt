package com.example.fuelmanagmentsoftware.activities.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.fuelmanagmentsoftware.R
import com.example.fuelmanagmentsoftware.activities.viewmodel.TodoViewModel
import com.example.fuelmanagmentsoftware.databinding.ActivityMainBinding
import com.example.fuelmanagmentsoftware.databinding.ActivitySignInBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)



        binding.btnLogout.setOnClickListener {
            signout()
            finish()
        }
    }


    private fun signout() {
        auth.signOut()
        startActivity(Intent(this, sign_in::class.java))
    }
}
