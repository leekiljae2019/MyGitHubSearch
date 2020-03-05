package com.afterwork.mygithubsearch.common

import com.afterwork.mygithubsearch.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelPart = module {
    viewModel {
        MainViewModel()
    }
}



var myDiModule = listOf(
    viewModelPart
)
