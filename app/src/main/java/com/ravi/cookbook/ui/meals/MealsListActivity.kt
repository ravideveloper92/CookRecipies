package com.ravi.cookbook.ui.meals

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ravi.cookbook.R
import com.ravi.cookbook.databinding.ActivityMealsListBinding
import com.ravi.cookbook.injection.ViewModelFactory


class MealsListActivity : AppCompatActivity(){

    private var filterByCategory: Boolean = false
    private var filterByArea: Boolean = false
    private var filter: String? = ""
    private lateinit var binding: ActivityMealsListBinding
    private lateinit var mealsViewModel: MealsViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_meals_list)
        val bundle : Bundle? = intent.extras
        if(bundle != null){
            if(bundle.containsKey("filter")){
                filter = bundle.getString("filter")
            }
            if(bundle.containsKey("filterByCategory")){
                filterByCategory = bundle.getBoolean("filterByCategory")
            }
            if(bundle.containsKey("filterByArea")){
                filterByArea = bundle.getBoolean("filterByArea")
            }
        }
        binding.mealsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(
            binding.mealsList.context,
            (binding.mealsList.layoutManager as LinearLayoutManager).orientation
        )
        binding.mealsList.addItemDecoration(dividerItemDecoration)
        mealsViewModel = ViewModelProviders.of(this,
            filter?.let { ViewModelFactory(this, it, filterByCategory, filterByArea) }).get(MealsViewModel::class.java)
        mealsViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        mealsViewModel.activityToStart.observe(this, Observer {
            if (it is ActivityState) openActivity(it)
        })

        binding.viewModel = mealsViewModel

    }
    private fun openActivity(activityState: ActivityState): Unit? {
        return when (activityState) {
            is ActivityState.Explicit -> {
                val intent = Intent(this, activityState.target.java)
                if (activityState.bundle != null) intent.apply { activityState.bundle }
                startActivity(intent)
                when (activityState.finish) {
                    true -> finish()
                    false -> TODO()
                }
            }
            is ActivityState.Implicit -> {
                val intent = Intent().setAction(activityState.action).setData(activityState.data)
                if (activityState.packages != null) intent.setPackage(activityState.packages)
                startActivity(intent)
            }

        }
    }


    private fun showError(errorMessage: Int) {
        when (errorMessage) {
            0->{binding.txtNoMealsFound.setText(getString(R.string.network_error))}
            1->{binding.txtNoMealsFound.setText(getString(R.string.uknown_error))}
            else ->binding.txtNoMealsFound.setText(getString(R.string.post_meals_error))
        }
        binding.imageFoodNotFound.visibility = View.VISIBLE
        binding.txtNoMealsFound.visibility = View.VISIBLE
    }

    private fun hideError(){
        //errorSnackbar?.dismiss()
        binding.imageFoodNotFound.visibility = View.GONE
        binding.txtNoMealsFound.visibility = View.GONE
    }


}
