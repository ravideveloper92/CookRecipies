package com.ravi.cookbook

import com.ravi.cookbook.model.CategoryDao
import com.ravi.cookbook.ui.category.CategoryListViewModel


class CategoryListViewModelTest {

    private fun createViewModel(categoryDao: CategoryDao) = CategoryListViewModel(categoryDao = categoryDao)



}