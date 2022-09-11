package com.example.catimagesearch.data.google_responce

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Queries {
    @SerializedName("request")
    @Expose
    var request: List<Request>? = null
}