package com.example.littlelemonandroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase : RoomDatabase(){
    abstract fun menuDao() : MenuDao
}