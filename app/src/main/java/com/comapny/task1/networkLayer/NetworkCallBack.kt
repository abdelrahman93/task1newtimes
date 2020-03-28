package com.comapny.task1.networkLayer

interface NetworkCallBack <T>{
    fun onSuccess(data:List<T>?)
    fun onError(error:String?)
}