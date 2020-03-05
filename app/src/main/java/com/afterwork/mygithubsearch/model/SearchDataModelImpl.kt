package com.afterwork.mygithubsearch.model

import com.afterwork.mygithubsearch.model.data.SearchResultData
import io.reactivex.Single

class SearchDataModelImpl(private val ISearchApi: ISearchApi) : ISearchDataModel{
    override fun getSearch(query: String): Single<SearchResultData> {
        return ISearchApi.getSearch(query, "stars", "desc", 1, 100)
    }
}