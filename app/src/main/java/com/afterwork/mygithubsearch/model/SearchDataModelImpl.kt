package com.afterwork.mygithubsearch.model

import com.afterwork.mygithubsearch.common.API_BASE_URL
import com.afterwork.mygithubsearch.model.data.SearchResultData
import io.reactivex.Single

class SearchDataModelImpl(private val ISearchApi: ISearchApi) : ISearchDataModel{
    override fun getSearch(q: String, page: Int, per_page: Int): Single<SearchResultData> {
        return ISearchApi.getSearch("${q}+language:kotlin", "stars", "desc", page, per_page)
    }

    override fun getSearchV2(q: String, page: Int, per_page: Int): Single<SearchResultData> {
        val fullUrl = "${API_BASE_URL}search/repositories?q=${q}+language:kotlin&sort=stars&order=desc&page=${page}&per_page=${per_page}"
        return ISearchApi.getSearchV2(fullUrl)
    }
}