package com.ravi.cookbook.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravi.cookbook.model.Category
import com.ravi.cookbook.model.CategoryDao

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

}