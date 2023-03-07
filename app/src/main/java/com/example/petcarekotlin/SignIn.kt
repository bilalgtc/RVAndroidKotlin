package com.example.petcarekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.petcarekotlin.databinding.ActivitySignInBinding
import com.example.roomapp.data.UserViewModel



class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
//    private lateinit var userRepository: UserRepository
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        val submit = findViewById<AppCompatButton>(R.id.sign_in)

        submit.setOnClickListener {

                val  email = binding.email2Ed.text.toString()
                val  password = binding.pass2Ed.text.toString()


            userViewModel.loginUser(email, password)



        }
        userViewModel.loginSuccess.observe(this) { success ->
            if (success) {
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, Dashboard::class.java))
                finish()

            } else {
                Toast.makeText(applicationContext, "User not found", Toast.LENGTH_SHORT).show()
            }
        }



        binding.txt1.setOnClickListener {
            startActivity(Intent(applicationContext, SignUp::class.java))
            finish()
        }
        binding.txt2.setOnClickListener {
            startActivity(Intent(applicationContext, SignUp::class.java))
            finish()
        }

    }
}