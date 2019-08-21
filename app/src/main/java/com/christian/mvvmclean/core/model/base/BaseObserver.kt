package com.christian.mvvmclean.core.model.base


abstract class BaseObserver<Any> {
   open fun onSuccess(value: Any) {
      //implemented in child
   }

   open fun onError(throwable: Throwable) {
      //implemented in child
   }

   open fun onComplete() {
      //implemented in child
   }
}