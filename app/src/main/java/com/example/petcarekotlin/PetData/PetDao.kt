package com.example.petcarekotlin.PetData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.petcarekotlin.LoginData.User


@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(petModel: PetModel)

    @Query("SELECT * FROM pet_detail ORDER BY id ASC")
    fun readAllData(): LiveData<List<PetModel>>

}