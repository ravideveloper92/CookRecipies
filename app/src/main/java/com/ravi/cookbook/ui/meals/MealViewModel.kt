package com.ravi.cookbook.ui.meals

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.MutableLiveData
import com.ravi.cookbook.R
import com.ravi.cookbook.base.BaseViewModel
import com.ravi.cookbook.model.Meal
import com.ravi.cookbook.model.Recipe
import com.ravi.cookbook.network.CookBookApi
import com.ravi.cookbook.utils.FILTER
import com.ravi.cookbook.utils.FILTER_BY_CATEGORY
import com.ravi.cookbook.utils.MEALS_LIST
import com.ravi.cookbook.utils.MealClickListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MealViewModel(
    private val mealId: String) : BaseViewModel(), MealClickListener {
    lateinit var recipe: Recipe
    lateinit var mealsListAdapter: MealsListAdapter
    private val TAG = "MealViewModel"
    @Inject
    lateinit var cookBookApi: CookBookApi

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val mealDetailsData : MutableLiveData<List<Meal>> = MutableLiveData()
   /// val errorClickListener = View.OnClickListener { loadMealDetails() }
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    private val idMeal = MutableLiveData<String>()
    private val strMeal = MutableLiveData<String>()
    private val strMealThumb = MutableLiveData<String>()
    private val strYoutube = MutableLiveData<String>()

    init {
      //  loadMealDetails()
    }

    fun bind(
        recipe: Recipe,
        mealsListAdapter: MealsListAdapter
    ){
        this.recipe=recipe
        idMeal.value = recipe.recipe_id
        strMeal.value = recipe.title
        strMealThumb.value = recipe.image_url
        strYoutube.value = recipe.source_url
    }

    fun getIdMeal(): MutableLiveData<String> {
        return idMeal
    }

    fun getStrMeal(): MutableLiveData<String> {
        return strMeal
    }

    fun getStrMealThumb(): MutableLiveData<String> {
        return strMealThumb
    }

    fun getStrYoutube(): MutableLiveData<String> {
        return strYoutube
    }

    override fun onCleared() {
        super.onCleared()
        if(::subscription.isInitialized){
            subscription.dispose()
        }
    }


    private fun onRetrieveMealsStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveMealsFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveMealsSuccess(mealList: List<Meal>){
        mealDetailsData.value = mealList
    }

    private fun onRetrieveMealsError(error: Throwable?) {
        Log.d(TAG, "onRetrieveMealsError: "+ error.toString())
        errorMessage.value = R.string.post_meals_error
    }

    override fun onClickRandomMeal(view: View, viewModel: MealViewModel?) {
        val intent = Intent(view.context, MealDetailsActivity::class.java)
        intent.putExtra(FILTER, viewModel?.getIdMeal().toString())
        intent.putExtra(FILTER_BY_CATEGORY, true)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            view.context as Activity, view, MEALS_LIST)
        view.context.startActivity(intent, options.toBundle())
    }

    fun onClickRemoveMeal(view: View, viewModel: MealViewModel?) {
        Log.d(TAG, "onClickRemoveMeal: ")
        recipe?.let {
            if (::recipe.isInitialized) {
                mealsListAdapter.removeMeals(recipe)
            }
        }
    }

    fun onClickSearchMeal(view: View, viewModel: String?) {
        Log.d(TAG, "onClickSearchMeal: ")
        val uri = Uri.parse(viewModel)
        mealsListAdapter.mealsViewModel.activityToStart.value = ActivityState.Implicit(Intent.ACTION_VIEW,"com.android.chrome", uri)
    }

    fun setAdatpter(mealsListAdapter: MealsListAdapter) {
        this.mealsListAdapter=mealsListAdapter
    }
}