package com.christian.mvvmclean.domain.usecase

import com.christian.mvvmclean.core.model.Result
import com.christian.mvvmclean.core.model.base.BaseUseCaseX
import com.christian.mvvmclean.domain.model.PostsOverview
import com.christian.mvvmclean.domain.repository.PostRepository


class GetAllPosts constructor(private val postRepository: PostRepository) : BaseUseCaseX<PostsOverview, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<PostsOverview> {
      return postRepository.getPosts()
   }
}