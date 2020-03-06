package com.afterwork.mygithubsearch.model

import com.afterwork.mygithubsearch.model.data.SearchResultData
import io.reactivex.Single

interface ISearchDataModel {
    fun getSearch(query: String, page: Int, per_page: Int): Single<SearchResultData>
}