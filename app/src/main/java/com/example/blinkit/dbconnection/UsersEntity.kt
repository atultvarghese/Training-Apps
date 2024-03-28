package com.example.blinkit.dbconnection

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UsersEntity {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_name")
    var user_name : String = ""

    @ColumnInfo(name= "password")
    var password : String = ""
}