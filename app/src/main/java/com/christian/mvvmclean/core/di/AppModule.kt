package com.christian.mvvmclean.core.di

import com.christian.mvvmclean.data.repository.PostApi
import com.christian.mvvmclean.data.repository.PostRepositoryImpl
import com.christian.mvvmclean.domain.repository.PostRepository
import com.christian.mvvmclean.domain.usecase.GetAllPosts
import com.christian.mvvmclean.presentation.overview.OverviewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single { createWebService<PostApi>(get(named("retrofit"))) }
    single<PostRepository> {
        PostRepositoryImpl(
            get()
        )
    }
    single { GetAllPosts(get()) }
    viewModel { OverviewViewModel(get()) }
}