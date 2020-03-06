package com.afterwork.mygithubsearch.model

import com.afterwork.mygithubsearch.model.data.SearchResultData
import io.reactivex.Single

class SearchDataModelImpl(private val ISearchApi: ISearchApi) : ISearchDataModel{
    override fun getSearch(q: String, page: Int, per_page: Int): Single<SearchResultData> {
        return ISearchApi.getSearch("${q}+language:kotlin", "stars", "desc", page, per_page)
    }
}