package com.example.fuelmanagmentsoftware.activities.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fuelmanagmentsoftware.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class sign_up : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        binding.btnLginOnSgnup.setOnClickListener {
            startActivity(Intent(this, sign_in::class.java))
            finish()}
        binding.btnRegister.setOnClickListener {
            Log.d("btnclick", "button clicked")
            val email: String = binding.inoutemail.text.toString()
            val userName: String = binding.inputusername.text.toString()
            val password: String = binding.inoutpass.text.toString()
            val reEnterPass: String = binding.inputrepass.text.toString()

            if (email.isEmpty() || userName.isEmpty() || password.isEmpty() || reEnterPass.isEmpty()) {
                Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_SHORT).show()
            } else if (password != reEnterPass) {
                Toast.makeText(this, "Password Re-Entered not same", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("SingUP","im in else")
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, sign_in::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Registration Faild : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }


        }
    }