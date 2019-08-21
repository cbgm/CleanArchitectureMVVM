package com.christian.mvvmclean.data.repository

import com.christian.mvvmclean.core.model.Result
import com.christian.mvvmclean.core.utils.mapToResult
import com.christian.mvvmclean.data.mapper.PostDtoMapper
import com.christian.mvvmclean.domain.model.Post
import com.christian.mvvmclean.domain.model.PostsOverview
import com.christian.mvvmclean.domain.repository.PostRepository

class PostRepositoryImpl(private val postApi: PostApi): PostRepository {

    override suspend fun getPosts(): Result<PostsOverview> {
        val response = postApi.getPostsAsync().await()

        return response.mapToResult {
            PostsOverview(
                it.count(),
                (response.body()!!.map { post ->
                    PostDtoMapper.transform(
                        post
                    )
                } as ArrayList<Post>))
        }
    }
}