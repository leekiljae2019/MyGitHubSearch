package com.afterwork.mygithubsearch.model

import com.afterwork.mygithubsearch.model.data.SearchResultData
import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ISearchApi{
    @GET("search/repositories")
    fun getSearch(@Query("q") q: String,
                  @Query("sort") sort: String,
                  @Query("order") order: String,
                  @Query("page") page: Int,
                  @Query("per_page") per_page: Int): Single<SearchResultData>
}