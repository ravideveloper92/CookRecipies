package com.ravi.cookbook.utils

import android.view.View
import com.ravi.cookbook.ui.category.CategoryViewModel

interface CategoryClickListeners{
    fun onClickCategory(view: View, viewModel: CategoryViewModel)
}