package com.comapny.task1.networkLayer

import com.comapny.task1.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/svc/mostpopular/v2/viewed/1.json")
    fun getNewsApi(@Query("api-key") apiKey: String): Call<NewsResponse>
}

//http://api.nytimes.com/svc/mostpopular/v2/viewed/7.json?api-key=73NYhIVj3y6dOKZZ969pucG2mM4NuMwB