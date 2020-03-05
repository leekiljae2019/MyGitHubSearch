package com.afterwork.mygithubsearch.viewmodel

import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.LiveData
import com.afterwork.mygithubsearch.viewmodel.base.BaseViewModel
import com.afterwork.mygithubsearch.viewmodel.base.NotNullMutableLiveData

class MainViewModel : BaseViewModel(){

    companion object{
        val TAG = "MainViewModel"
    }

    val keyword : NotNullMutableLiveData<String> = NotNullMutableLiveData("")

    private val _refreshing : NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing : LiveData<Boolean> get() = _refreshing


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
    }

}