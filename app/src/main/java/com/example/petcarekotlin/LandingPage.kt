package com.example.petcarekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petcarekotlin.databinding.ActivityLandingPageBinding

class LandingPage : AppCompatActivity() {
    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerEmailbtn.setOnClickListener {
            val i = Intent(applicationContext,SignUp::class.java)
            startActivity(i)
            finish()
        }

        binding.regfbTxt1.setOnClickListener {
            val i = Intent(applicationContext,SignIn::class.java)
            startActivity(i)
            finish()
        }

        binding.regfbTxt2.setOnClickListener {
            val i = Intent(applicationContext,SignIn::class.java)
            startActivity(i)
            finish()
        }


    }
}