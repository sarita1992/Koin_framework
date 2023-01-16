package com.example.unlimint.networkCall

import io.reactivex.Single
import retrofit2.http.GET

interface BasicApiService {

    @GET("api")
    fun getJokes() : Single<String>
}