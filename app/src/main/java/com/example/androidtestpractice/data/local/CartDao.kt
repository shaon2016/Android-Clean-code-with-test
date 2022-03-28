package com.example.androidtestpractice.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cart: Cart)

    @Delete
    suspend fun delete(cart: Cart)

    @Query("SELECT * FROM CART_TABLE")
    fun all() : LiveData<List<Cart>>

    @Query("SELECT SUM(price * qty) from cart_table")
    fun totalPrice() : LiveData<Double>
}