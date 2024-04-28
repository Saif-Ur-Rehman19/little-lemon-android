package com.example.littlelemonandroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.littlelemonandroid.MenuItemNetwork

@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuItem")
    fun getAllMenuItems() : LiveData<List<MenuItem>>

    @Insert
    fun saveMenuItems(menuItem: List<MenuItem>)

    @Delete
    fun deleteMenuItem(menuItem: MenuItem)
}