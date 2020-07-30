package com.ravi.cookbook.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @get:Query("SELECT * FROM category")
    val all: List<Category>

    @Insert
    fun insertAll(vararg categories: Category)
}