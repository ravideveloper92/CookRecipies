package com.ravi.cookbook.ui.home

import androidx.lifecycle.MutableLiveData
import com.ravi.cookbook.base.BaseViewModel
import com.ravi.cookbook.model.Area
import com.ravi.cookbook.network.CookBookApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AreaViewModel : BaseViewModel() {

    @Inject
    lateinit var cookBookApi: CookBookApi

    val areasData : MutableLiveData<List<Area>> = MutableLiveData()

    private lateinit var subscription: Disposable

    private val strArea = MutableLiveData<String>()

    /*init {
        loadAreas()
    }*/

    override fun onCleared() {
        super.onCleared()
        if(::subscription.isInitialized){
            subscription.dispose()
        }
    }

    private fun onRetrieveMealsStart(){

    }

    private fun onRetrieveMealsFinish(){
    }

    private fun onRetrieveMealsSuccess(areaList: List<Area>){
        areasData.value = areaList
    }

    private fun onRetrieveMealsError(error: Throwable?) {
    }

}