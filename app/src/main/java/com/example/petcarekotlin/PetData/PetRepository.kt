package com.example.petcarekotlin.PetData

import androidx.lifecycle.LiveData
import com.example.petcarekotlin.LoginData.User

class PetRepository(private val petDao: PetDao) {

    val readAllData: LiveData<List<PetModel>> = petDao.readAllData()

    suspend fun addUser(petModel: PetModel){
        petDao.addData(petModel)
    }

    suspend fun updatePet(petModel: PetModel){
        petDao.updatePet(petModel)
    }

    suspend fun deletePet(petModel: PetModel){
        petDao.deletePet(petModel)
    }


}