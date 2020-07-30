package com.ravi.cookbook.ui.meals

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ravi.cookbook.R
import com.ravi.cookbook.base.BaseViewModel
import com.ravi.cookbook.model.Meal
import com.ravi.cookbook.model.Recipe
import com.ravi.cookbook.model.Recipes
import com.ravi.cookbook.network.CookBookApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MealsViewModel(private val filter : String, private val categoryFilter : Boolean, private val areaFilter : Boolean) : BaseViewModel() {
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val mealsListAdapter: MealsListAdapter = MealsListAdapter(this)

    @Inject
    lateinit var cookBookApi: CookBookApi

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    //val errorClickListener = View.OnClickListener { loadMeals() }
    var activityToStart = MutableLiveData<ActivityState>()


    private lateinit var subscription: Disposable

    init{
        loadAreaFilteredMeals()
/*
        when {
            areaFilter -> loadAreaFilteredMeals()
            categoryFilter -> loadCategoryFilteredMeals()
            else -> loadMeals()
        }
*/
    }

    override fun onCleared() {
        super.onCleared()
        if(::subscription.isInitialized){
            subscription.dispose()
        }
    }

    private fun loadAreaFilteredMeals() {
        if(filter.isNotEmpty()){
         /*   subscription = cookBookApi.getMealsByArea(filter).concatMap {
                    listMeals ->
                Observable.just(listMeals.meals)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveMealsStart() }
                .doOnTerminate { onRetrieveMealsFinish() }
                .subscribe(
                    { result ->
                        onRetrieveMealsSuccess(result as List<Meal>) },
                    { error -> onRetrieveMealsError(error) })*/
            subscription = cookBookApi.getRecipe(filter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveMealsStart() }
                .doOnTerminate { onRetrieveMealsFinish() }
                .subscribe(
                    { result -> onRetrieveSuccess(result) },
                    { error -> onRetrieveMealsError(error) })

        }
    }


    private fun onRetrieveMealsStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveMealsFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveSuccess(recipes: Recipes){
        if (!recipes.recipes.isEmpty()) {
            mealsListAdapter.updateMeals(recipes.recipes as ArrayList<Recipe>)
        } else {
            errorMessage.value = 3
        }
    }


    private fun onRetrieveMealsSuccess(mealList: List<Meal>){
      //  mealsListAdapter.updateMeals(mealList)
    }

    private fun onRetrieveMealsError(error: Throwable?) {
        if (error is IllegalArgumentException) {
            errorMessage.value = 1
        } else {
            errorMessage.value = 0
        }

    }

}