package com.comapny.task1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.comapny.task1.models.NewsResults
import com.comapny.task1.networkLayer.NetworkCallBack
import com.comapny.task1.networkLayer.Requests.NewsRequestInterface

class NewsViewModel(private val newsRequest: NewsRequestInterface) :ViewModel() {

    private val _news = MutableLiveData<List<NewsResults>>().apply { value = emptyList() }
    val news: LiveData<List<NewsResults>> = _news

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList
    /*
    If you require that the data be loaded only once, you can consider calling the method
    "loadMuseums()" on constructor. Also, if you rotate the screen, the service will not be called.

    init {
        //loadMuseums()
    }
     */

    fun loadMuseums(){
        _isViewLoading.postValue(true)
        newsRequest.retrieveNews(object:NetworkCallBack<NewsResults>{

            override fun onSuccess(data: List<NewsResults>?) {
                _isViewLoading.postValue(false)

                if(data!=null){
                    if(data.isEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _news.postValue(data)
                    }
                }
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( error)
            }

        })
    }

}