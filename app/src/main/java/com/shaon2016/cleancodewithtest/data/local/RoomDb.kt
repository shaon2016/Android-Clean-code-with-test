package com.shaon2016.cleancodewithtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cart::class], version = 1)
abstract class RoomDb : RoomDatabase(){
    abstract fun cartDao(): CartDao
    abstract fun productDao() : ProductDao
}