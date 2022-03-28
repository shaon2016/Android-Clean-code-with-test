package com.example.androidtestpractice.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
    val title: String,
    val price: Double,
    val qty: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
