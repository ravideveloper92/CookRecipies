package com.ravi.cookbook.ui.category

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravi.cookbook.R
import com.ravi.cookbook.databinding.ItemCategoryBinding
import com.ravi.cookbook.model.Category
import com.ravi.cookbook.ui.meals.MealsListActivity

class CategoryListAdapter: RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    private lateinit var categoryList : List<Category>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_category, parent, false)
        return ViewHolder(binding, viewModel = CategoryViewModel())
    }

    override fun getItemCount(): Int {
        return if(::categoryList.isInitialized) categoryList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    fun updateCategoryList(categoryList:List<Category>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding : ItemCategoryBinding,
                     private val viewModel: CategoryViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category : Category){
            viewModel.bind(category)
            binding.viewModel = viewModel
        }


    }
}