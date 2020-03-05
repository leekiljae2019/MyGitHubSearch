package com.afterwork.mygithubsearch.view

import android.os.Bundle
import android.util.Log
import com.afterwork.mygithubsearch.R
import com.afterwork.mygithubsearch.databinding.ActivityMainBinding
import com.afterwork.mygithubsearch.view.base.BaseActivity
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
    }
}
