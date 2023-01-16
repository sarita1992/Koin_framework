package com.example.unlimint.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unlimint.RxSingleSchedulers
import com.example.unlimint.networkCall.BasicApiService

import io.reactivex.schedulers.Schedulers

class MainViewModel(val apiService: BasicApiService, val schedulers: RxSingleSchedulers) : ViewModel(){
    val userList = MutableLiveData<String>()

    fun fetchUsers() {
        apiService.getJokes()
            .subscribeOn(Schedulers.io())
            .compose(schedulers.applySchedulers())
            .subscribe({ result -> userList.postValue(result) },
                { throwable ->

                })
    }
}