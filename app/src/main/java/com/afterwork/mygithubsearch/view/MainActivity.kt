package com.afterwork.mygithubsearch.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import com.afterwork.mygithubsearch.R
import com.afterwork.mygithubsearch.databinding.ActivityMainBinding
import com.afterwork.mygithubsearch.paging.RepoPagingAdapter
import com.afterwork.mygithubsearch.view.base.BaseActivity
import com.afterwork.mygithubsearch.viewmodel.MainViewModel
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

        list.adapter = RepoPagingAdapter(binding.vmMain as MainViewModel, {
            Log.d(TAG, "onItemClick(${it})")
            var intent = Intent(this@MainActivity, RepoWebActivity::class.java)
            intent.putExtra(RepoWebActivity.LINK, it)
            startActivity(intent)
        })

        binding.vmMain?.load()?.observe(this, Observer {
            Log.d(TAG, "Observe event: ${it.size}")
            (list.adapter as RepoPagingAdapter).submitList(it)
        })

        binding.vmMain?.result?.observe(this, Observer {
            Log.d(TAG, "ime hide")
            if(it.isNullOrEmpty() == false) {
                Handler().postDelayed(Runnable {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(edt_search.windowToken, 0)
                }, 100)
            }
        })
    }
}

