package com.example.petcarekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.petcarekotlin.PetData.PetModel
import com.example.petcarekotlin.PetData.PetViewModel
import com.example.petcarekotlin.databinding.ActivityAddDetailsBinding

class AddDetails : AppCompatActivity() {

    private lateinit var binding: ActivityAddDetailsBinding
    private lateinit var petViewModel: PetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        petViewModel = ViewModelProvider(this).get(PetViewModel::class.java)
        binding.submitBtn.setOnClickListener {

            val name = binding.nameEd.text.toString()
            val species = binding.speciesEd.text.toString()
            val breed = binding.breedEd.text.toString()
            val size = binding.sizeEd.text.toString()

            val petModel = PetModel(0,name, species, breed, size)
            petViewModel.addUser(petModel)

        }
    }
}