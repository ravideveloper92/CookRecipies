package com.ravi.cookbook.network

import com.ravi.cookbook.model.*
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface CookBookApi {
    /**
     * Get the list of the categories from the API
     */
    @GET("search?")
    fun getRecipe(@Query("q") key:String): Single<Recipes>

}