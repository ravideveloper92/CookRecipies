package com.ravi.cookbook.ui.home

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ravi.cookbook.base.BaseViewModel
import com.ravi.cookbook.model.Area
import com.ravi.cookbook.model.Recipes
import com.ravi.cookbook.network.CookBookApi
import com.ravi.cookbook.ui.meals.MealDetailsActivity
import com.ravi.cookbook.ui.meals.MealViewModel
import com.ravi.cookbook.ui.meals.MealsListActivity
import com.ravi.cookbook.utils.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : BaseViewModel(), HomeClickListeners, MealClickListener,APIStatus {
    private val TAG = "HomeViewModel"
    @Inject
    lateinit var cookBookApi: CookBookApi
    private lateinit var subscription: Disposable

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    fun setMealSearch(value: String) {
        _text.value = value
    }
    override fun onCleared() {
        super.onCleared()
        if(::subscription.isInitialized){
            subscription.dispose()
        }
    }

    override fun onClickBtnSearch(view: View, viewModel: HomeViewModel) {
        if (_text.value!!.isNotEmpty()) {
            val intent = Intent(view.context, MealsListActivity::class.java)
            intent.putExtra("filter", _text.value.toString().trim())
            view.context.startActivity(intent)
        }
    }

    override fun onClickRandomMeal(view: View, viewModel: MealViewModel?) {
        val intent = Intent(view.context, MealDetailsActivity::class.java)
        intent.putExtra(FILTER, viewModel?.getIdMeal().toString())
        intent.putExtra(FILTER_BY_CATEGORY, true)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            view.context as Activity, view, MEALS_LIST)
        view.context.startActivity(intent, options.toBundle())
    }

    override fun onRetrieveSuccess(list: Any) {
        Log.d(TAG, "onRetrieveSuccess: "+ list.toString())
    }

    override fun onRetrieveStart() {

    }

    override fun onRetrieveFinish() {
    }

    override fun onRetrieveError(error: Throwable?) {
        Log.d(TAG, "onRetrieveError: "+ error?.message)

    }

}