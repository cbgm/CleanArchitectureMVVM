package com.christian.mvvmclean.core.model.single

import com.christian.mvvmclean.core.model.base.BaseObserver


open class DefaultSingleObserver<T> : BaseObserver<T>() {
   override fun onSuccess(value: T) {
      //implemented in child
   }

   override fun onError(throwable: Throwable) {
      //implemented in child
   }

   final override fun onComplete() {
      //not needed
   }
}