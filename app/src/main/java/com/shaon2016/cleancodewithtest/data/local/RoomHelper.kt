package com.shaon2016.cleancodewithtest.data.local

import android.content.Context
import androidx.room.Room

class RoomHelper(private val context: Context) {

    private val db =
        Room.databaseBuilder(context, RoomDb::class.java, "AppDb").allowMainThreadQueries().build()

    fun getDatabase(): RoomDb {
        return db
    }
}