package com.ravi.cookbook.ui.category

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.MutableLiveData
import com.ravi.cookbook.R
import com.ravi.cookbook.base.BaseViewModel
import com.ravi.cookbook.model.Category
import com.ravi.cookbook.ui.meals.MealsListActivity
import com.ravi.cookbook.utils.CategoryClickListeners
import com.ravi.cookbook.utils.FILTER
import com.ravi.cookbook.utils.FILTER_BY_CATEGORY
import com.ravi.cookbook.utils.MEALS_LIST

class CategoryViewModel : BaseViewModel(), CategoryClickListeners {
    private val idCategory = MutableLiveData<String>()
    private val strCategory = MutableLiveData<String>()
    private val strCategoryThumb = MutableLiveData<String>()
    private val strCategoryDescription = MutableLiveData<String>()

    fun bind(category: Category){
        idCategory.value = category.idCategory
        strCategory.value = category.strCategory
        strCategoryThumb.value = category.strCategoryThumb
        strCategoryDescription.value = category.strCategoryDescription
    }

    fun getIdCategory():MutableLiveData<String>{
        return idCategory
    }

    fun getStrCategory():MutableLiveData<String>{
        return strCategory
    }

    fun getStrCategoryThumb():MutableLiveData<String>{
        return strCategoryThumb
    }

    fun getStrCategoryDescription():MutableLiveData<String>{
        return strCategoryDescription
    }

    override fun onClickCategory(view: View, viewModel: CategoryViewModel) {
        val intent = Intent(view.context, MealsListActivity::class.java)
        intent.putExtra(FILTER, viewModel!!.getStrCategory().toString())
        intent.putExtra(FILTER_BY_CATEGORY, true)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            view.context as Activity, view, MEALS_LIST
        )
        view.context.startActivity(intent, options.toBundle())
    }

}