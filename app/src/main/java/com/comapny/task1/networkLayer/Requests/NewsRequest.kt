package com.comapny.task1.networkLayer.Requests

import com.comapny.task1.models.NewsResponse
import com.comapny.task1.models.NewsResults
import com.comapny.task1.networkLayer.NetworkCallBack
import com.comapny.task1.networkLayer.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG="CONSOLE"

class NewsRequest :
    NewsRequestInterface {

    private var call:Call<NewsResponse>?=null


    override fun retrieveNews(callback: NetworkCallBack<NewsResults>) {
        call=ServiceGenerator.build()?.getNewsApi("73NYhIVj3y6dOKZZ969pucG2mM4NuMwB")
        call?.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                response?.body()?.let {
                    if(response.isSuccessful){
                        callback.onSuccess(it.results)
                    }else{
                        callback.onError(it.status)
                    }
                }
            }


            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                callback.onError(t.message)
            }

        })    }

   override fun cancel() {

        call?.let {
            it.cancel()
        }
    }
}