package com.comapny.task1.models

import com.google.gson.annotations.SerializedName


data class NewsResults (


    @SerializedName("uri") val uri : String,
    @SerializedName("url") val url : String,
    @SerializedName("id") val id : Long,
    @SerializedName("asset_id") val asset_id : Long,
    @SerializedName("source") val source : String,
    @SerializedName("published_date") val published_date : String,
    @SerializedName("updated") val updated : String,
    @SerializedName("section") val section : String,
    @SerializedName("subsection") val subsection : String,
    @SerializedName("nytdsection") val nytdsection : String,
    @SerializedName("adx_keywords") val adx_adx_keywordswords : String,
    @SerializedName("column") val column : String,
    @SerializedName("byline") val byline : String,
    @SerializedName("type") val type : String,
    @SerializedName("title") val title : String,
    @SerializedName("abstract") val abstract : String,
    @SerializedName("des_facet") val des_facet : List<String>,
    @SerializedName("org_facet") val org_facet : List<String>,
    @SerializedName("per_facet") val per_facet : List<String>,
    @SerializedName("geo_facet") val geo_facet : List<String>,
    @SerializedName("media") val media : List<MediaResult>,
    @SerializedName("eta_id") val eta_id : Int
)