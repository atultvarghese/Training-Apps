package com.example.blinkit.dbconnection

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CartEntity {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id : Int = 0

    @ColumnInfo(name = "user")
    var user : String = ""

    @ColumnInfo(name = "url")
    var url : String = ""

    @ColumnInfo(name = "title")
    var title : String = ""

    @ColumnInfo(name = "qty")
    var qty : Int = 0

}