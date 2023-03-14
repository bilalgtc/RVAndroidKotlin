package com.example.petcarekotlin.PetData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pet_detail")
data class PetModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val species: String,
    val breed: String,
    val size: String,
    val image: ByteArray?,
    val gender: String,
    val neutered: Boolean,
    val vacci: Boolean,
    val dogs: Boolean,
    val cats: Boolean,
    val child: Boolean,
    val childern: Boolean
)
