package com.christian.mvvmclean.core

import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {

    sealed class State<out T : Any> {
        data class Fail<out T : Any>(val reason: String) : State<T>()
        data class Loading<out T : Any>(val none: Unit? = null) : State<T>()
        data class Success<T : Any>(val data: T) : State<T>()
    }
}
