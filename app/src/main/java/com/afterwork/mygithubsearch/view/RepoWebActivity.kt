package com.afterwork.mygithubsearch.view

import android.os.Bundle
import android.util.Log
import com.afterwork.mygithubsearch.R
import com.afterwork.mygithubsearch.databinding.ActivityRepowebBinding
import com.afterwork.mygithubsearch.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class RepoWebActivity : BaseActivity<ActivityRepowebBinding>(){

    companion object{
        val TAG = "RepoWebActivity"
        val LINK = "link"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_repoweb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(url: ${intent.getStringExtra(LINK)})")

        binding.vmRepoWeb = getViewModel{ parametersOf(intent.getStringExtra(LINK)) }
        binding.lifecycleOwner = this
    }
}