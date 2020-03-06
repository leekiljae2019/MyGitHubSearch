package com.afterwork.mygithubsearch.model

import com.afterwork.mygithubsearch.model.data.SearchResultData
import io.reactivex.Single

interface ISearchDataModel {
    fun getSearch(q: String, page: Int, per_page: Int): Single<SearchResultData>
}