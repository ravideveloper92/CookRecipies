package com.ravi.cookbook.ui.meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravi.cookbook.BR
import com.ravi.cookbook.R
import com.ravi.cookbook.databinding.ItemMealBinding
import com.ravi.cookbook.model.Recipe

class MealsListAdapter(val mealsViewModel: MealsViewModel) : RecyclerView.Adapter<MealsListAdapter.ViewHolder>() {
    private lateinit var mealList : ArrayList<Recipe>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMealBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_meal, parent, false)
        val viewModel1=MealViewModel(mealList[0].recipe_id!!)
            viewModel1.setAdatpter(this)
        return ViewHolder(binding, viewModel = viewModel1)
    }



    override fun getItemCount(): Int {
        return if(::mealList.isInitialized) mealList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mealList[position],this)
    }

    fun updateMeals(mealList:ArrayList<Recipe>){
        this.mealList = mealList
        notifyDataSetChanged()
    }


    fun removeMeals(recipes: Recipe) {
        notifyItemRemoved(this.mealList.indexOf(recipes))
    }

    class ViewHolder (private val binding : ItemMealBinding,
                     private val viewModel: MealViewModel): RecyclerView.ViewHolder(binding.root) {

        fun bind(
            recipes: Recipe,
            mealsListAdapter: MealsListAdapter
        ){
            viewModel.bind(recipes,mealsListAdapter)
            binding.apply {
                setVariable(BR.viewModel, this@ViewHolder.viewModel)
                executePendingBindings()
            }

        }
    }

}
