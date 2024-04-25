package com.example.fuelmanagmentsoftware.activities.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fuelmanagmentsoftware.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class sign_in : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    private val binding: ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = auth.currentUser
        if (currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)

binding.btnLginOnSgnin.setOnClickListener {

    val userName = binding.inputusername.text.toString()
    val password = binding.inputpass.text.toString()

    if (userName.isEmpty()|| password.isEmpty()){
        Toast.makeText(this, "Please Fill the Details", Toast.LENGTH_SHORT).show()
    }
    else{
        auth.signInWithEmailAndPassword(userName,password)
            .addOnCompleteListener {
                task->
                if (task.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
            }else{
                    Toast.makeText(this, "Sign-In Failed ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
        binding.btnSgnupOnSgnin.setOnClickListener{
            startActivity(Intent(this, sign_up::class.java))
        }
    }
}