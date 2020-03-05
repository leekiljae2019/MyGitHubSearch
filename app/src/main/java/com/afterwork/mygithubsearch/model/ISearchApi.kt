package com.afterwork.mygithubsearch.model

import com.afterwork.mygithubsearch.model.data.SearchResultData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ISearchApi{
    @GET("search/repositories")
    fun getSearch(@Query("q") query: String,
                  @Query("sort") sort: String,
                  @Query("order") order: String,
                  @Query("page") page: Int,
                  @Query("per_page") per_page: Int): Single<SearchResultData>
}