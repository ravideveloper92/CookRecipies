package com.ravi.cookbook.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AreaList {
    @SerializedName("meals")
    @Expose
    var meals: List<Area> = mutableListOf()
}