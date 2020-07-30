package com.ravi.cookbook.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Area {
    @SerializedName("strArea")
    @Expose
    internal var strArea: String? = null
}