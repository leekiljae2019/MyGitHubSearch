package com.afterwork.mygithubsearch.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.mygithubsearch.R
import com.afterwork.mygithubsearch.databinding.ItemRepositoryBinding
import com.afterwork.mygithubsearch.model.data.RepoData
import com.afterwork.mygithubsearch.viewmodel.MainViewModel

class RepoPagingAdapter (val vmMain: MainViewModel, val onClickListener: (String)-> Unit) : PagedListAdapter<RepoData, RepoPagingAdapter.MainViewHolder>(
    DIFF_CALLBACK
){

    companion object{
        val TAG = "RepoPagingAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoData>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: RepoData, newItem: RepoData) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: RepoData, newItem: RepoData) = oldItem == newItem
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
        holder.binding.vmMain = vmMain
        holder.itemView.setOnClickListener {
            onClickListener.invoke(getItem(position)?.html_url?:"")
        }
    }
}

abstract class BindingViewHolder<out T: ViewDataBinding>(_view: View): RecyclerView.ViewHolder(_view){
    val binding: T = DataBindingUtil.bind(_view)!!
}