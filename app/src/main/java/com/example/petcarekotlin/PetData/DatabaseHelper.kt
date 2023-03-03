package com.example.petcarekotlin.PetData

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petcarekotlin.LoginData.UserDao
import com.example.petcarekotlin.LoginData.UserDatabase


@Database(entities = [PetModel::class], version = 1, exportSchema = false)
abstract class DatabaseHelper :RoomDatabase(){

    abstract fun petDao():PetDao


    companion object {




        @Volatile
        private var INSTANCE: DatabaseHelper? = null

        fun getDatabase(context: Context): DatabaseHelper {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    "pet_datas"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}