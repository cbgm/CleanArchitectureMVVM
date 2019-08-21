package com.christian.mvvmclean.presentation.overview.model

import com.christian.mvvmclean.presentation.detail.model.PostEntity

data class PostsOverviewEntity(val count: Int, val events: ArrayList<PostEntity>)