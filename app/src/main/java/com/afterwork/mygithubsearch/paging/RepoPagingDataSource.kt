package com.afterwork.mygithubsearch.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.afterwork.mygithubsearch.model.ISearchDataModel
import com.afterwork.mygithubsearch.model.data.SearchData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepoPagingDataSource(val keyword: String, val model: ISearchDataModel, val listener: (Int)->Unit): PageKeyedDataSource<Int, SearchData>() {

    companion object {
        val TAG = "RepoPagingDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SearchData>
    ) {
        Log.d(TAG, "loadInitial(pageKey: 1, loadSize: ${params.requestedLoadSize})")

        if(keyword.isNullOrEmpty()){
            return
        }

        model.getSearch(keyword, 1, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed(total: $total_count, pageSize: ${items.size})")
//                    for(iteㄴ
                    listener.invoke(total_count)
                    callback.onResult(items, null, (2))
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SearchData>) {
        Log.d(TAG, "loadAfter(pageKey: ${params.key}, loadSize: ${params.requestedLoadSize})")

        model.getSearch(keyword, params.key, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed(total: $total_count, pageSize: ${items.size})")
//                    for(item in items){
//                        Log.d(TAG, "id: ${item.id}, name: ${item.full_name}")
//                    }
                    listener.invoke(total_count)
                    callback.onResult(items, (params.key+1))
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SearchData>) {
        Log.d(TAG, "loadBefore(pageKey: ${params.key}, loadSize: ${params.requestedLoadSize})")
    }

    fun clear(){
        invalidate()
    }
}