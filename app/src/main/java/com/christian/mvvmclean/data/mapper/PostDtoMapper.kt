package com.christian.mvvmclean.data.mapper

import com.christian.mvvmclean.data.model.PostDto
import com.christian.mvvmclean.domain.model.Post

class PostDtoMapper {

    companion object {
        fun transform(postDto: PostDto): Post {
            return Post(
                userId = postDto.userId,
                id = postDto.id,
                title = postDto.title,
                body = postDto.body
            )
        }
    }
}