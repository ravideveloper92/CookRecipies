package com.ravi.cookbook.model

import androidx.room.Entity

@Entity
data class CategoryList (
    val categories: List<Category>
)