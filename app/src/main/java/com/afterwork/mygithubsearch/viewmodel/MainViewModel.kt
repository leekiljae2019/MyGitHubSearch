package com.afterwork.mygithubsearch.viewmodel

import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.mygithubsearch.common.Util
import com.afterwork.mygithubsearch.model.ISearchDataModel
import com.afterwork.mygithubsearch.model.data.SearchData
import com.afterwork.mygithubsearch.viewmodel.base.BaseViewModel
import com.afterwork.mygithubsearch.viewmodel.base.NotNullMutableLiveData
import com.afterwork.mygithubsearch.viewmodel.base.RepoListAdapter
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val model: ISearchDataModel) : BaseViewModel(){

    companion object{
        val TAG = "MainViewModel"
    }

    val keyword : NotNullMutableLiveData<String> = NotNullMutableLiveData("")

    private val _refreshing : NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing : LiveData<Boolean> get() = _refreshing

    private val _items: NotNullMutableLiveData<List<SearchData>> = NotNullMutableLiveData<List<SearchData>>(mutableListOf())
    val items: LiveData<List<SearchData>> get() = _items


    fun onRefreshing(){
        Log.d(TAG, "onRefreshing()")
        _refreshing.value = true

        if(keyword.value.isEmpty() == false) {
            search()
        }
    }

    fun editorAction(actionId: Int): Boolean{
        Log.d(TAG, "actionSearch($actionId, ${keyword.value})")
        if(EditorInfo.IME_ACTION_SEARCH == actionId && keyword.value.isEmpty() == false){
            _refreshing.value = true
            search()
            return true
        }
        return false
    }

    fun search(){
        addDisposable(model.getSearch(keyword.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
                    _refreshing.postValue(false)

                    _items.postValue(it.items)

                    Log.d(TAG, "total count: ${it.total_count}")
                    for(item in it.items){
                        Log.d(TAG, "id: ${item.id}, stars: ${item.stargazers_count}, owner: ${item.owner.id}")
                    }
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
                _refreshing.postValue(false)
            }))
    }

}

@BindingAdapter("avatarImage")
fun avatarImage(view: SimpleDraweeView, url: String){
    view.setImageURI(url)
}

@BindingAdapter(value = ["starCount", "forkCount", "openIssueCount"], requireAll = true)
fun countText(view: TextView, stars: Int, forks: Int, issues: Int){
    view.text = "Star: ${Util.prettyNumber(stars)}, Fork: ${Util.prettyNumber(forks)}, Issue: ${Util.prettyNumber(issues)}"
}

@BindingAdapter(value = ["viewModel", "repoItems"], requireAll = true)
fun setRecyclerViewAdapter(view: RecyclerView, vm: MainViewModel, items: List<SearchData>) {
    Log.d("BindingAdapterEx", "mainViewAdapter")

    view.adapter?.run {
        if (this is RepoListAdapter) {
            Log.d("BindingAdapterEx", "item size: ${items.size}")
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        Log.d("BindingAdapterEx", "ScrollListenerAdapter create")
        RepoListAdapter(items).apply { view.adapter = this }
    }

}