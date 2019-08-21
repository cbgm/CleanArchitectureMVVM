package com.christian.mvvmclean.presentation.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.christian.mvvmclean.core.BaseViewModel
import com.christian.mvvmclean.core.model.base.BaseObserver
import com.christian.mvvmclean.domain.model.PostsOverview
import com.christian.mvvmclean.domain.usecase.GetAllPosts
import com.christian.mvvmclean.presentation.overview.mapper.PostsOverviewDomainMapper
import com.christian.mvvmclean.presentation.overview.model.PostsOverviewEntity


class OverviewViewModel(private val getAllPosts: GetAllPosts) : BaseViewModel() {

    val posts = MutableLiveData<State<PostsOverviewEntity>>()

    private inner class GetPostsObserver : BaseObserver<PostsOverview, PostsOverviewEntity>(posts) {
        override fun onSuccess(value: PostsOverview) {
            posts.value = State.Success(PostsOverviewDomainMapper.transform(value))
        }
    }

    fun loadPosts() {
        posts.value = State.Loading()
        getAllPosts.execute(viewModelScope, GetPostsObserver(), Unit, 50000)
    }
}