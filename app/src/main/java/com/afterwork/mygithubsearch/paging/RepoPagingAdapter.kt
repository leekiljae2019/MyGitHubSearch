package com.afterwork.mygithubsearch.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.afterwork.mygithubsearch.R
import com.afterwork.mygithubsearch.databinding.ItemRepositoryBinding
import com.afterwork.mygithubsearch.model.data.SearchData
import com.afterwork.mygithubsearch.viewmodel.base.BindingViewHolder

class RepoPagingAdapter (val onClickListener: (String)-> Unit) : PagedListAdapter<SearchData, RepoPagingAdapter.MainViewHolder>(
    DIFF_CALLBACK
){

    companion object{
        val TAG = "RepoPagingAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchData>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: SearchData, newItem: SearchData) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: SearchData, newItem: SearchData) = oldItem == newItem
        }

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

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder(position: $position)")
        holder.binding.item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.invoke(getItem(position)?.html_url?:"")
        }
    }
}