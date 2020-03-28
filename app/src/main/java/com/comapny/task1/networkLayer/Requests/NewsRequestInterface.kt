package com.comapny.task1.networkLayer.Requests

import com.comapny.task1.models.NewsResults
import com.comapny.task1.networkLayer.NetworkCallBack

interface NewsRequestInterface {

    fun retrieveNews(callback: NetworkCallBack<NewsResults>)
    fun cancel()

}