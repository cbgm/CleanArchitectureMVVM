package com.christian.mvvmclean.core.model.base

import androidx.lifecycle.MutableLiveData
import com.christian.mvvmclean.core.BaseViewModel.State


abstract class BaseObserver<in T ,in S: Any>(private val stateHolder: MutableLiveData<State<S>>) {
   open fun onSuccess(value: T) {
   }

   open fun onError(throwable: Throwable) {
      stateHolder.value = State.Fail(throwable.message.toString())
   }

   open fun onComplete() {
   }
}