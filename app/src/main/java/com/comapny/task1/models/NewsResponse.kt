package com.comapny.task1.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


 data class NewsResponse (

    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("num_results") val num_results : Int,
    @SerializedName("results") val results : List<NewsResults>
)