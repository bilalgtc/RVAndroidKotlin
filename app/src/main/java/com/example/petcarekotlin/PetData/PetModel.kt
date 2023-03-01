package com.example.petcarekotlin.PetData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pet_table")
data class PetModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val species: String,
    val breed: String,
    val size: String
)
