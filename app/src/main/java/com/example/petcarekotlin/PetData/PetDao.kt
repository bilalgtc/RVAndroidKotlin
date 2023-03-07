package com.example.petcarekotlin.PetData

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(petModel: PetModel)

    @Query("SELECT * FROM pet_detail ORDER BY id ASC")
    fun readAllData(): LiveData<List<PetModel>>

    @Update
    suspend fun updatePet(petModel: PetModel)

    @Delete
     fun deletePet(petModel: PetModel)

}