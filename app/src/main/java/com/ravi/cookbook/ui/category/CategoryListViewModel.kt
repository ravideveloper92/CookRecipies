package com.ravi.cookbook.ui.category

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ravi.cookbook.R
import com.ravi.cookbook.base.BaseViewModel
import com.ravi.cookbook.model.Category
import com.ravi.cookbook.model.CategoryDao
import com.ravi.cookbook.network.CookBookApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryListViewModel(private val categoryDao: CategoryDao) : BaseViewModel() {
    @Inject
    lateinit var cookBookApi: CookBookApi
    val categoryListAdapter: CategoryListAdapter = CategoryListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
//    val errorClickListener = View.OnClickListener { loadCategories() }

    private lateinit var subscription: Disposable


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


    private fun onRetrieveCategoryListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCategoryListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCategoryListSuccess(categoryList: List<Category>){
        categoryListAdapter.updateCategoryList(categoryList)
    }

    private fun onRetrieveCategoryListError(error: Throwable) {
        errorMessage.value = R.string.post_categories_error
    }
}