package com.christian.mvvmclean.data.repository

import com.christian.mvvmclean.data.model.PostDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPostsAsync(): Deferred<Response<List<PostDto>>>
}