package com.example.catimagesearch.data.google_responce

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseModel {
    @SerializedName("kind")
    @Expose
    var kind: String? = null

//    @SerializedName("url")
//    @Expose
//    var url: String? = null

    @SerializedName("queries")
    @Expose
    var queries: Queries? = null

    @SerializedName("searchInformation")
    @Expose
    var searchInformation: SearchInformation? = null

    @SerializedName("items")
    @Expose
    var items: List<Item?>? = null

}