package com.example.blinkit.dbconnection

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(usersEntity: UsersEntity) : Long

    @Query("select * from UsersEntity")
    fun getAllData() : List<UsersEntity>

    @Query("select * from UsersEntity where user_name like :user and password like :pass")
    fun checkUserExisting(user : String, pass : String) : List<UsersEntity>

}