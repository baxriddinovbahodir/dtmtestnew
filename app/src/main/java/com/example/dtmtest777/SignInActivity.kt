package com.example.dtmtest777

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dtmtest777.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textViewSignUp.setOnClickListener {
            startActivity(Intent(this@SignInActivity , SignUpActivity::class.java))
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        startActivity( Intent(this@SignInActivity , MainActivity::class.java))
                    }else{
                        Toast.makeText(this , it.exception.toString() , Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                Toast.makeText(this , "Empty Fields Are not Allowed !!" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (firebaseAuth.currentUser != null){
            startActivity( Intent(this@SignInActivity,MainActivity::class.java))
        }
    }
}