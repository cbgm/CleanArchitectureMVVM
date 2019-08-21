package com.christian.mvvmclean.core.model.base

import com.christian.mvvmclean.core.model.Result
import com.christian.mvvmclean.core.model.emit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

abstract class BaseUseCaseX<T, S: Any, in Params> {

    abstract suspend fun buildUseCaseObservable(param: Params): Result<Any>

    fun execute(
        scope: CoroutineScope,
        observer: BaseObserver<T, S>? = null,
        param: Params,
        timeout: Long? = null
    ) {
        val backgroundJob = scope.async { buildUseCaseObservable(param) }
        scope.launch {
            if (timeout != null) {
                try {
                    val result = withTimeout(timeout) { backgroundJob.await() }
                    result.emit(observer)
                } catch (e: Exception) {
                    observer?.onError(e)
                }
            } else {
                val result = backgroundJob.await()
                result.emit(observer)
            }
        }
    }
}