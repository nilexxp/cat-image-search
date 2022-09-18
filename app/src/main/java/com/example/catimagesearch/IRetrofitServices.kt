package com.example.catimagesearch

import com.example.catimagesearch.data.google_responce.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitServices {

    @GET("customsearch/v1")
    fun getData(
        @Query("key") key: String?,
        @Query("cx") cx: String?,
        @Query("q") query: String?,
        @Query("searchType") searchType: String?
    ): Call<ResponseModel>
}