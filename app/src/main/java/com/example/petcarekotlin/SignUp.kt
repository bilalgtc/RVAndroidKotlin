package com.example.petcarekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.petcarekotlin.LoginData.User
import com.example.petcarekotlin.databinding.ActivitySignUpBinding
import com.example.roomapp.data.UserViewModel

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var userViewModel:UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.signupBtn.setOnClickListener {

            val name = binding.nameEd.text.toString()
            val email = binding.emailEd.text.toString()
            val mobile = binding.mobileEd.text.toString()
            val password = binding.passEd.text.toString()

            val user = User(0,name,email,mobile, password)
            userViewModel.addUser(user)

            binding.nameEd.text=null
            binding.emailEd.text=null
            binding.mobileEd.text=null
            binding.passEd.text=null

            startActivity(Intent(applicationContext,SignIn::class.java))
            finish()

        }
    }
}