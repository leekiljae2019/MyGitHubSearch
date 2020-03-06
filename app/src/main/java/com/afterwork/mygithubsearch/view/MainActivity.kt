package com.afterwork.mygithubsearch.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.afterwork.mygithubsearch.R
import com.afterwork.mygithubsearch.databinding.ActivityMainBinding
import com.afterwork.mygithubsearch.paging.RepoPagingAdapter
import com.afterwork.mygithubsearch.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object{
        val TAG = "MainActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate()")

        binding.vmMain = getViewModel()
        binding.lifecycleOwner = this

        list.adapter = RepoPagingAdapter({
            Log.d(TAG, "onItemClick(${it})")
        })

        binding.vmMain?.load()?.observe(this, Observer {
            Log.d(TAG, "Observe event: ${it.size}")
            (list.adapter as RepoPagingAdapter).submitList(it)
            binding.vmMain?.loaded()
        })
    }
}
