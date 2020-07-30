package com.ravi.cookbook.utils

import android.view.View
import com.ravi.cookbook.ui.meals.MealViewModel

interface MealClickListener {

    fun onClickRandomMeal(view: View, viewModel: MealViewModel?)

}