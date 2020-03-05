package com.afterwork.mygithubsearch.common

import com.afterwork.mygithubsearch.model.ISearchApi
import com.afterwork.mygithubsearch.model.ISearchDataModel
import com.afterwork.mygithubsearch.model.SearchDataModelImpl
import com.afterwork.mygithubsearch.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val API_BASE_URL = "https://api.github.com/"


var modelPart = module {
    factory<ISearchDataModel> {
        SearchDataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}


var retrofitPart = module {
    single<ISearchApi>{
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ISearchApi::class.java)
    }
}

var myDiModule = listOf(
    modelPart,
    viewModelPart,
    retrofitPart
)
