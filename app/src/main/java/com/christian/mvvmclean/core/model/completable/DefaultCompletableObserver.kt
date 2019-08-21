package com.christian.mvvmclean.core.model.completable

import com.christian.mvvmclean.core.model.base.BaseObserver


open class DefaultCompletableObserver : BaseObserver<Unit>() {
   override fun onError(throwable: Throwable) {
      //implemented in child
   }

   override fun onComplete() {
      //implemented in child
   }

   final override fun onSuccess(value: Unit) {
      //not needed
   }
}