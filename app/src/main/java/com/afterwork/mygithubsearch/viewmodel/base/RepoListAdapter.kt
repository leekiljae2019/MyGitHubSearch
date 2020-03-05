package com.afterwork.mygithubsearch.viewmodel.base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.mygithubsearch.R
import com.afterwork.mygithubsearch.databinding.ItemRepositoryBinding
import com.afterwork.mygithubsearch.model.data.SearchData

class RepoListAdapter (var items: List<SearchData> = arrayListOf())
    : RecyclerView.Adapter<RepoListAdapter.MainViewHolder>() {

    companion object {
        val TAG = "RepoListAdapter"
    }

    class MainViewHolder(view: View) : BindingViewHolder<ItemRepositoryBinding>(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        Log.d(TAG, "onCreateViewHolder(viewType: $viewType)")
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repository,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder(position: $position)")
        holder.binding.item = items[position]
    }
}

abstract class BindingViewHolder<out T: ViewDataBinding>(_view: View): RecyclerView.ViewHolder(_view){
    val binding: T = DataBindingUtil.bind(_view)!!
}