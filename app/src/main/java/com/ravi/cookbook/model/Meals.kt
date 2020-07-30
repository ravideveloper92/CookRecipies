package com.ravi.cookbook.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meals  {
    @SerializedName("meals")
    @Expose
    var meals: List<Meal> = mutableListOf()
}