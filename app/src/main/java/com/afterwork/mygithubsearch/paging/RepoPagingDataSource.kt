package com.afterwork.mygithubsearch.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.afterwork.mygithubsearch.model.ISearchDataModel
import com.afterwork.mygithubsearch.model.data.RepoData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepoPagingDataSource(val keyword: String, val model: ISearchDataModel, val listener: (Int)->Unit): PageKeyedDataSource<Int, RepoData>() {

    companion object {
        val TAG = "RepoPagingDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepoData>
    ) {
        if(keyword.isNullOrEmpty()){
            return
        }
        Log.d(TAG, "loadInitial(pageKey: 1, loadSize: ${params.requestedLoadSize})")

        model.getSearchV2(keyword, 1, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed(total: $total_count, pageSize: ${items.size})")
                    listener.invoke(total_count)
                    callback.onResult(items, null, (2))
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoData>) {
        Log.d(TAG, "loadAfter(pageKey: ${params.key}, loadSize: ${params.requestedLoadSize})")

        model.getSearchV2(keyword, params.key, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed(total: $total_count, pageSize: ${items.size})")
                    listener.invoke(total_count)
                    callback.onResult(items, (params.key+1))
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoData>) {
        Log.d(TAG, "loadBefore(pageKey: ${params.key}, loadSize: ${params.requestedLoadSize})")
    }

    fun clear(){
        invalidate()
    }
}