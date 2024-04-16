package com.example.blinkit.dbconnection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UsersEntity::class, CartEntity::class], version = 2)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDbCreate() : UsersDao
    abstract fun cartDbCreate() : CartDao

    companion object {
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getDataBase(context : Context): UsersDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "users"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}
