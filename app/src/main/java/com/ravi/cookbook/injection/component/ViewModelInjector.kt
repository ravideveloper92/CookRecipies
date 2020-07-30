package com.ravi.cookbook.injection.component

import dagger.Component
import com.ravi.cookbook.injection.module.NetworkModule
import com.ravi.cookbook.ui.meals.MealViewModel
import com.ravi.cookbook.ui.category.CategoryListViewModel
import com.ravi.cookbook.ui.home.AreaViewModel
import com.ravi.cookbook.ui.home.HomeViewModel
import com.ravi.cookbook.ui.meals.MealsViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified CategoriesViewModel.
     * @param categoriesViewModel CategoriesViewModel in which to inject the dependencies
     */
    fun inject(categoriesViewModel: CategoryListViewModel)
    /**
     * Injects required dependencies into the specified HomeViewModel.
     * @param homeViewModel HomeViewModel in which to inject the dependencies
     */
    fun inject(homeViewModel: HomeViewModel)
    /**
     * Injects required dependencies into the specified MealViewModel.
     * @param mealViewModel MealsViewModel in which to inject the dependencies
     */
    fun inject(mealViewModel: MealViewModel)
    /**
     * Injects required dependencies into the specified MealsViewModel.
     * @param mealsViewModel MealsViewModel in which to inject the dependencies
     */
    fun inject(mealsViewModel: MealsViewModel)
    /**
     * Injects required dependencies into the specified AreaViewModel.
     * @param areaViewModel AreaViewModel in which to inject the dependencies
     */
    fun inject(areaViewModel: AreaViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}