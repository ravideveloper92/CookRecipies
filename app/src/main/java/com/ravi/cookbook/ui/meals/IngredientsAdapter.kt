package com.ravi.cookbook.ui.meals

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ravi.cookbook.R
import kotlinx.android.synthetic.main.ingredient_list_item.view.*

class IngredientsAdapter(private val ingredients : ArrayList<String>, private val measurements : ArrayList<String>, private val context: Context) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtIngredient.text = ingredients[position]
        holder.txtMeasurement.text = measurements[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtIngredient: TextView = view.text_ingredient
        val txtMeasurement: TextView = view.text_measurement
    }

}
