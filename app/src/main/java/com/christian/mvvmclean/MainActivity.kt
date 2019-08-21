package com.christian.mvvmclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.christian.mvvmclean.presentation.overview.OverviewViewModel
import com.christian.mvvmclean.presentation.overview.model.PostsOverviewEntity
import org.koin.android.viewmodel.ext.android.viewModel
import com.christian.mvvmclean.core.BaseViewModel.State

class MainActivity : AppCompatActivity() {

    private val overviewViewModel: OverviewViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overviewViewModel.posts.observe(this, Observer(::postsChanged))
    }

    override fun onResume() {
        super.onResume()
        overviewViewModel.loadPosts()
    }

    private fun postsChanged(state: State<PostsOverviewEntity>) {
        when(state) {
           is State.Success -> {
                for (item in state.data.events){
                    Log.e("test", item.title)
                }
            }
            is State.Loading -> {
                Log.e("test", "Loading")
            }
            is State.Fail -> {
                Log.e("test", state.reason)
            }
        }
    }
}
