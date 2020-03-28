package com.comapny.task1.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


 data class MediaList (

    @SerializedName("url") val url : String,
    @SerializedName("format") val format : String,
    @SerializedName("height") val height : Int,
    @SerializedName("width") val width : Int
)