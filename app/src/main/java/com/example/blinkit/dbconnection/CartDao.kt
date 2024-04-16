package com.example.blinkit.dbconnection

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToCart(cartEntity: CartEntity) : Long

    @Query("select * from CartEntity where qty > 0 and user = \"Atul\"")
    fun getCartData() : List<CartEntity>

}