package com.example.petcarekotlin.PetData

import androidx.lifecycle.LiveData

class PetRepository(private val petDao: PetDao) {

    val readAllData: LiveData<List<PetModel>> = petDao.readAllData()

    suspend fun addUser(petModel: PetModel) {
        petDao.addData(petModel)
    }

    suspend fun updatePet(petModel: PetModel) {
        petDao.updatePet(petModel)
    }

    fun deletePet(petModel: PetModel) {
        petDao.deletePet(petModel)
    }


}