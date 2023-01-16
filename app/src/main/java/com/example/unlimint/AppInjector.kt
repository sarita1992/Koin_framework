package com.example.unlimint



import com.example.unlimint.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    single { createBasicAuthService() }
    single { RxSingleSchedulers.DEFAULT }
}

val viewModelModule = module {

    viewModel {
        MainViewModel(get(), get())
    }
}