package com.christian.mvvmclean.presentation.overview.mapper

import com.christian.mvvmclean.domain.model.Post
import com.christian.mvvmclean.domain.model.PostsOverview
import com.christian.mvvmclean.presentation.detail.model.PostEntity
import com.christian.mvvmclean.presentation.overview.model.PostsOverviewEntity

class PostsOverviewDomainMapper {

    companion object {
        fun transform(postsOverview: PostsOverview): PostsOverviewEntity {
            return PostsOverviewEntity(
                postsOverview.count,
                postsOverview.events.flatMap {
                    listOf(
                        Companion.transform(it)
                    )
                }.toCollection(ArrayList())
            )
        }

        fun transform(post: Post): PostEntity {
            return PostEntity(
                userId = post.userId,
                id = post.id,
                title = post.title,
                body = post.body
            )
        }
    }
}