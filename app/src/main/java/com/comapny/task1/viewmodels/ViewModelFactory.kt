package com.comapny.task1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.comapny.task1.networkLayer.Requests.NewsRequestInterface

class ViewModelFactory(private val newsRequest: NewsRequestInterface):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRequest) as T
    }
}