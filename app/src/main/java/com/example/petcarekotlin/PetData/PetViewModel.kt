package com.example.petcarekotlin.PetData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<PetModel>>
    private val repository:PetRepository

    init {
        val petDao = DatabaseHelper.getDatabase(application).petDao()
        repository = PetRepository(petDao)
        readAllData = repository.readAllData

    }

    fun addUser(petModel: PetModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(petModel)
        }
    }


    fun updatePet(petModel: PetModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePet(petModel)
        }
    }
    fun deletePet(petModel: PetModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePet(petModel)
        }
    }

}