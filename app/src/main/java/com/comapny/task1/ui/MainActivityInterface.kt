package com.comapny.task1.ui

import com.comapny.task1.models.NewsResults
import com.comapny.task1.networkLayer.NetworkCallBack

interface MainActivityInterface {

    fun navigateToNewsDetails(title: String,desc: String,by: String,date: String,url: String)

}