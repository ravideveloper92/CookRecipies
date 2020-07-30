package com.ravi.cookbook.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ravi.cookbook.model.database.AppDatabase
import com.ravi.cookbook.ui.category.CategoryListViewModel
import com.ravi.cookbook.ui.home.AreaViewModel
import com.ravi.cookbook.ui.home.HomeViewModel
import com.ravi.cookbook.ui.meals.MealViewModel
import com.ravi.cookbook.ui.meals.MealsViewModel

class ViewModelFactory(private val activity: AppCompatActivity, private val filter : String, private val filterByCategory : Boolean, private val filterByArea : Boolean): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "categories").build()
            @Suppress("UNCHECKED_CAST")
            return CategoryListViewModel(db.categoryDao()) as T
        }

        if (modelClass.isAssignableFrom(MealsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealsViewModel(filter, filterByCategory, filterByArea) as T
        }

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel() as T
        }

        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealViewModel(filter) as T
        }

        if (modelClass.isAssignableFrom(AreaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AreaViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
