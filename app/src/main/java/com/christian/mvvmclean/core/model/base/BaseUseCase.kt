package com.christian.mvvmclean.core.model.base

import com.christian.mvvmclean.core.model.Result
import com.christian.mvvmclean.core.model.emit
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*


abstract class BaseUseCase<T, S: Any, in Params> {

   var job: Job? = null

   abstract suspend fun buildUseCaseObservable(param: Params): Result<Any>

   fun execute(observer: BaseObserver<T, S>? = null, param: Params) {
      dispose()
      job = CoroutineScope(Dispatchers.Main).launch {
         val result = buildUseCaseObservable(param)
         result.emit(observer)
      }
   }


   fun executeWithTimeout(observer: BaseObserver<T, S>? = null, param: Params, timeout: Long) {
      dispose()
      job = CoroutineScope(Dispatchers.Main).launch {
         val task = GlobalScope.async(Dispatchers.IO) { buildUseCaseObservable(param) }
         //background thread
         try {
            val result = withTimeout(timeout) { task.await() }
            result.emit(observer)
         } catch (e: Exception) {
            observer?.onError(e)
         }
      }
   }

   fun executeLong(observer: BaseObserver<T, S>? = null, param: Params) {
      dispose()
      job = CoroutineScope(Dispatchers.IO).launch {
         val result = buildUseCaseObservable(param)
         result.emit(observer)
      }
   }

   fun dispose() = job?.takeIf { !it.isCancelled }?.cancel()
}