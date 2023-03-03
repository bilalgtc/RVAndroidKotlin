package com.example.petcarekotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petcarekotlin.databinding.ActivityDetailsBinding

class Details : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val species = intent.getStringExtra("species")
        val breed = intent.getStringExtra("breed")
        val size = intent.getStringExtra("size")
        val gender = intent.getStringExtra("gender")
        val images: ByteArray? = intent.getByteArrayExtra("image")

        val neutered = intent.getStringExtra("neutered")
        val vacci = intent.getStringExtra("vacci")
        val dog = intent.getStringExtra("dogs")
        val cat = intent.getStringExtra("cats")
        val child = intent.getStringExtra("child")
        val children = intent.getStringExtra("children")


        binding.dognameTxt.text = name
        binding.breedTxt.text = species
        binding.breedNameTxt.text = breed
        binding.size2Txt.text = size
        binding.gender2Txt.text = gender

        binding.imageView10.setImageBitmap(getImage(images))




        if (neutered == "true"){
            binding.s1.setImageResource(R.drawable.check_circle_24)
        }else{
            binding.s1.setImageResource(R.drawable.cross_cicle)
        }

        if (vacci == "true"){
            binding.s2.setImageResource(R.drawable.check_circle_24)
        }else{
            binding.s2.setImageResource(R.drawable.cross_cicle)
        }


        if (dog == "true"){
            binding.s3.setImageResource(R.drawable.check_circle_24)
        }else{
            binding.s3.setImageResource(R.drawable.cross_cicle)
        }

        if (cat == "true"){
            binding.s4.setImageResource(R.drawable.check_circle_24)
        }else{
            binding.s4.setImageResource(R.drawable.cross_cicle)
        }

        if (child == "true"){
            binding.s5.setImageResource(R.drawable.check_circle_24)
        }else{
            binding.s5.setImageResource(R.drawable.cross_cicle)
        }

        if (children == "true"){
            binding.s6.setImageResource(R.drawable.check_circle_24)
        }else{
            binding.s6.setImageResource(R.drawable.cross_cicle)
        }

    }

    //for byte to bitmap
    fun getImage(images: ByteArray?): Bitmap? {
        return images?.let { BitmapFactory.decodeByteArray(images, 0, it.size) }
    }
}