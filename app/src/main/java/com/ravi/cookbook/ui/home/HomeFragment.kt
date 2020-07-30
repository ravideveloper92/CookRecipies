package com.ravi.cookbook.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import com.ravi.cookbook.R
import com.ravi.cookbook.databinding.FragmentHomeBinding
import com.ravi.cookbook.injection.ViewModelFactory
import com.ravi.cookbook.model.Meal
import com.ravi.cookbook.ui.meals.MealViewModel
import com.ravi.cookbook.ui.meals.MealsListActivity
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mealViewModel: MealViewModel
    private lateinit var areaViewModel: AreaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity, "", false, false)).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this
        binding.editMeal.doOnTextChanged { text, start, count, after ->
            binding.viewModel!!.setMealSearch(text.toString())
        }

        mealViewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity, "", false, false)).get(
            MealViewModel::class.java)
        lateinit var mealData : Meal
        mealViewModel.mealDetailsData.observe(this, Observer {
                mealDetailsData ->
            if(mealDetailsData != null){
                mealData = mealDetailsData[0]
                Picasso.get()
                    .load(mealData.strMealThumb)
                    .placeholder(R.drawable.ic_solid_white)
                    .error(R.drawable.ic_solid_white)
                    .into(binding.randomImage)

                binding.randomTitle.text = mealData.strMeal
            }
        })

        areaViewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity, "", false, false)).get(
            AreaViewModel::class.java)

        areaViewModel.areasData.observe(this, Observer {
            areaData ->
            for (genre in areaData) {
                val chip = Chip(context)
                chip.text = genre.strArea
                chip.setChipBackgroundColorResource(R.color.colorPrimaryLight)
                chip.setTextColor(Color.parseColor("#FFFFFF"))
                chip.setOnClickListener{
                    val intent =  Intent(binding.root.context, MealsListActivity::class.java)
                    intent.putExtra("filter", chip.text.toString())
                    intent.putExtra("filterByArea", true)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        binding.root.context as Activity,
                        binding.root,
                        "MealsList")
                    binding.root.context.startActivity(intent, options.toBundle())
                }
                binding.chipGroup.addView(chip)
            }
        })

        return binding.root
    }
}