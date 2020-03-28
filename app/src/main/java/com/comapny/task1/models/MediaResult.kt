package com.comapny.task1.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


 data class MediaResult (

    @SerializedName("type") val type : String,
    @SerializedName("subtype") val subtype : String,
    @SerializedName("caption") val caption : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("approved_for_syndication") val approved_for_syndication : Int,
    @SerializedName("media-metadata") val media_metadata : List<MediaList>

)