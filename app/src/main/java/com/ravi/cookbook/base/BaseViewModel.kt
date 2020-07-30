package com.ravi.cookbook.base

import androidx.lifecycle.ViewModel
import com.ravi.cookbook.injection.component.DaggerViewModelInjector
import com.ravi.cookbook.injection.component.ViewModelInjector
import com.ravi.cookbook.injection.module.NetworkModule
import com.ravi.cookbook.ui.meals.MealViewModel
import com.ravi.cookbook.ui.category.CategoryListViewModel
import com.ravi.cookbook.ui.home.AreaViewModel
import com.ravi.cookbook.ui.home.HomeViewModel
import com.ravi.cookbook.ui.meals.MealsViewModel


abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CategoryListViewModel -> injector.inject(this)
            is HomeViewModel -> injector.inject(this)
            is MealsViewModel -> injector.inject(this)
            is MealViewModel -> injector.inject(this)
            is AreaViewModel -> injector.inject(this)
        }
    }
}