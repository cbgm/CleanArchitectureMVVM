package com.christian.mvvmclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.christian.mvvmclean.presentation.overview.OverviewViewModel
import com.christian.mvvmclean.presentation.overview.model.PostsOverviewEntity
import org.koin.android.viewmodel.ext.android.viewModel
import com.christian.mvvmclean.core.BaseViewModel.State
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.christian.mvvmclean.presentation.overview.PostsAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import com.christian.mvvmclean.presentation.detail.model.PostEntity


class MainActivity : AppCompatActivity(), PostsAdapter.PostClickListener {

    private val overviewViewModel: OverviewViewModel by viewModel()
    private val adapter: PostsAdapter by lazy {
        PostsAdapter(this, arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        overviewViewModel.posts.observe(this, Observer(::postsChanged))
    }

    override fun onResume() {
        super.onResume()
        overviewViewModel.loadPosts()
    }

    private fun postsChanged(state: State<PostsOverviewEntity>) {
        when (state) {
            is State.Success -> {
                loading_text.visibility = View.GONE
                listview.visibility = View.VISIBLE
                adapter.replaceData(state.data.posts)
            }
            is State.Loading -> {
                loading_text.visibility = View.VISIBLE
                listview.visibility = View.GONE
            }
            is State.Fail -> {
                //test_text.appendLine(state.reason)
            }
        }
    }

    private fun initViews(){
        listview.layoutManager = LinearLayoutManager(this)
        listview.adapter = adapter
        listview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun TextView.appendLine(data: String) {
        this.append(data + "\n")
    }

    override fun onclick(postEntity: PostEntity) {
        Toast.makeText(this, postEntity.id.toString(), Toast.LENGTH_SHORT).show()
    }
}
