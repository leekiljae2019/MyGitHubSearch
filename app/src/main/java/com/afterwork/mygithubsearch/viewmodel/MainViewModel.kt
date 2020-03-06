package com.afterwork.mygithubsearch.viewmodel

import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.afterwork.mygithubsearch.common.Util
import com.afterwork.mygithubsearch.model.ISearchDataModel
import com.afterwork.mygithubsearch.model.data.RepoData
import com.afterwork.mygithubsearch.paging.RepoPagingDataSourceFactory
import com.afterwork.mygithubsearch.viewmodel.base.BaseViewModel
import com.afterwork.mygithubsearch.viewmodel.base.NotNullMutableLiveData
import com.facebook.drawee.view.SimpleDraweeView

class MainViewModel(private val model: ISearchDataModel) : BaseViewModel(){

    companion object{
        val TAG = "MainViewModel"
    }

    val keyword : NotNullMutableLiveData<String> = NotNullMutableLiveData("")

    private val _result : NotNullMutableLiveData<String> = NotNullMutableLiveData("")
    val result : LiveData<String> get() = _result

    private val _refreshing : NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing : LiveData<Boolean> get() = _refreshing

    val pagedListBuilder: LivePagedListBuilder<Int, RepoData>
    val factory: RepoPagingDataSourceFactory

    val pageConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(30)
        .setPrefetchDistance(4)
        .setPageSize(30)
        .build()

    init {
        factory = RepoPagingDataSourceFactory(keyword, model, {
            _result.value = keyword.value + " 검색 결과: ${it}"
            _refreshing.value = false
        })
        pagedListBuilder = LivePagedListBuilder<Int, RepoData>(factory, pageConfig)
    }

    fun onRefreshing(){
        Log.d(TAG, "onRefreshing()")
        _refreshing.value = true

        if(keyword.value.isEmpty() == false) {
            search()
        }
    }

    fun editorAction(view: View, actionId: Int): Boolean{
        Log.d(TAG, "actionSearch($actionId, ${keyword.value})")
        if(EditorInfo.IME_ACTION_SEARCH == actionId && keyword.value.isEmpty() == false){
            _refreshing.value = true
            search()
            return true
        }
        return false
    }

    fun search(){
        factory.reset()
    }

    fun load() = pagedListBuilder.setInitialLoadKey(1).build()

}

@BindingAdapter("avatarImage")
fun avatarImage(view: SimpleDraweeView, url: String){
    view.setImageURI(url)
}

@BindingAdapter(value = ["starCount", "forkCount", "openIssueCount"], requireAll = true)
fun countText(view: TextView, stars: Int, forks: Int, issues: Int){
    view.text = "Star: ${Util.prettyNumber(stars)}, Fork: ${Util.prettyNumber(forks)}, Issue: ${Util.prettyNumber(issues)}"
}
