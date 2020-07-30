package com.ravi.cookbook.ui.meals

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ravi.cookbook.R
import com.ravi.cookbook.databinding.ActivityMealDetailsBinding
import com.ravi.cookbook.injection.ViewModelFactory
import com.ravi.cookbook.model.Meal
import com.squareup.picasso.Picasso

class MealDetailsActivity : AppCompatActivity() {

    private var mealId: String = ""
    private lateinit var binding: ActivityMealDetailsBinding
    private lateinit var mealViewModel: MealViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_meal_details)
        val bundle : Bundle? = intent.extras
        if(bundle != null){
            if(bundle.containsKey("filter")){
                mealId = bundle.getString("filter")
            }
        }

        mealViewModel = ViewModelProviders.of(this, ViewModelFactory(this, mealId, false, false)).get(MealViewModel::class.java)

        mealViewModel.mealDetailsData.observe(this, Observer {
            mealDetailsData ->
            if(mealDetailsData != null){
                var mealData = mealDetailsData[0]
                Picasso.get()
                    .load(mealData.strMealThumb)
                    .placeholder(R.drawable.ic_solid_white)
                    .error(R.drawable.ic_solid_white)
                    .into(binding.imageMealThumb)

                binding.textMealTitle.text = mealData.strMeal

                binding.recyclerIngredients.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.recyclerIngredients.adapter = IngredientsAdapter(getIngredients(mealData), getMeasurements(mealData), this)

                binding.textMealPrep.text = mealData.strInstructions
            }
        })

        mealViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = mealViewModel
    }

    private fun getMeasurements(mealData: Meal): ArrayList<String> {
        var arrMeasurements : ArrayList<String> = ArrayList()
        if(!mealData.strMeasure1.isNullOrEmpty()) mealData.strMeasure1?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure2.isNullOrEmpty()) mealData.strMeasure2?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure3.isNullOrEmpty()) mealData.strMeasure3?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure4.isNullOrEmpty()) mealData.strMeasure4?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure5.isNullOrEmpty()) mealData.strMeasure5?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure6.isNullOrEmpty()) mealData.strMeasure6?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure7.isNullOrEmpty()) mealData.strMeasure7?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure8.isNullOrEmpty()) mealData.strMeasure8?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure9.isNullOrEmpty()) mealData.strMeasure9?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure10.isNullOrEmpty()) mealData.strMeasure10?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure11.isNullOrEmpty()) mealData.strMeasure11?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure12.isNullOrEmpty()) mealData.strMeasure12?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure13.isNullOrEmpty()) mealData.strMeasure13?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure14.isNullOrEmpty()) mealData.strMeasure14?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure15.isNullOrEmpty()) mealData.strMeasure15?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure16.isNullOrEmpty()) mealData.strMeasure16?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure17.isNullOrEmpty()) mealData.strMeasure17?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure18.isNullOrEmpty()) mealData.strMeasure18?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure19.isNullOrEmpty()) mealData.strMeasure19?.let { arrMeasurements.add(it) }
        if(!mealData.strMeasure20.isNullOrEmpty()) mealData.strMeasure20?.let { arrMeasurements.add(it) }

        return arrMeasurements

    }

    private fun getIngredients(mealData: Meal): ArrayList<String> {
        var arrIngredients : ArrayList<String> = ArrayList()
        if(!mealData.strIngredient1.isNullOrEmpty()) mealData.strIngredient1?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient2.isNullOrEmpty()) mealData.strIngredient2?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient3.isNullOrEmpty()) mealData.strIngredient3?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient4.isNullOrEmpty()) mealData.strIngredient4?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient5.isNullOrEmpty()) mealData.strIngredient5?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient6.isNullOrEmpty()) mealData.strIngredient6?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient7.isNullOrEmpty()) mealData.strIngredient7?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient8.isNullOrEmpty()) mealData.strIngredient8?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient9.isNullOrEmpty()) mealData.strIngredient9?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient10.isNullOrEmpty()) mealData.strIngredient10?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient11.isNullOrEmpty()) mealData.strIngredient11?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient13.isNullOrEmpty()) mealData.strIngredient13?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient14.isNullOrEmpty()) mealData.strIngredient14?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient15.isNullOrEmpty()) mealData.strIngredient15?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient16.isNullOrEmpty()) mealData.strIngredient16?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient17.isNullOrEmpty()) mealData.strIngredient17?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient18.isNullOrEmpty()) mealData.strIngredient18?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient19.isNullOrEmpty()) mealData.strIngredient19?.let { arrIngredients.add(it) }
        if(!mealData.strIngredient20.isNullOrEmpty()) mealData.strIngredient20?.let { arrIngredients.add(it) }

        return arrIngredients
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
      //  errorSnackbar?.setAction(R.string.retry, mealViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

}