package com.example.dtmtest777

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dtmtest777.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            startActivity(Intent(this@SignUpActivity , SignInActivity::class.java))
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passEt.text.toString()
            val corfirmPass = binding.passEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && corfirmPass.isNotEmpty()){
                if (pass == corfirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            startActivity(Intent(this@SignUpActivity , SignInActivity::class.java))

                        }else{
                            Toast.makeText(this , it.exception.toString() , Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this , "Password is not matching" , Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this , "Empty Fields Are not Allowed !!" , Toast.LENGTH_SHORT).show()
            }

        }
    }
}