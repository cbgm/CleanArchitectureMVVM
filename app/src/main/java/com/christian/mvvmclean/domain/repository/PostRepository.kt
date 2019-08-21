package com.christian.mvvmclean.domain.repository

import com.christian.mvvmclean.core.model.Result
import com.christian.mvvmclean.domain.model.PostsOverview

interface PostRepository {

    suspend fun getPosts(): Result<PostsOverview>
}