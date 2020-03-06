package com.afterwork.mygithubsearch.model

import com.afterwork.mygithubsearch.model.data.SearchResultData
import io.reactivex.Single

class SearchDataModelImpl(private val ISearchApi: ISearchApi) : ISearchDataModel{
    override fun getSearch(query: String, page: Int, per_page: Int): Single<SearchResultData> {
        return ISearchApi.getSearch(query, "stars", "desc", page, per_page)
    }
}