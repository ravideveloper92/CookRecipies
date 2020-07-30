package com.ravi.cookbook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category (
    val strCategory: String,
    @field:PrimaryKey
    val idCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)