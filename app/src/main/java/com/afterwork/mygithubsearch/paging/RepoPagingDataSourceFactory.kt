package com.afterwork.mygithubsearch.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.afterwork.mygithubsearch.model.ISearchDataModel
import com.afterwork.mygithubsearch.model.data.SearchData

class RepoPagingDataSourceFactory  (val keyword: LiveData<String>, val model: ISearchDataModel, val listener: (Int)->Unit): DataSource.Factory<Int, SearchData>() {
    val sourceLiveData = MutableLiveData<RepoPagingDataSource>()
    var dataSource: RepoPagingDataSource? = null

    override fun create(): DataSource<Int, SearchData> {
        dataSource = RepoPagingDataSource(keyword.value!!, model, listener)
        sourceLiveData.postValue(dataSource)
        return dataSource as RepoPagingDataSource
    }

    fun reset() {
        dataSource?.clear()
    }
}