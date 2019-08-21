package com.christian.mvvmclean.core.model.completable

import com.christian.mvvmclean.core.model.base.BaseUseCase


abstract class CompletableUseCase<in Params>: BaseUseCase<Unit, Params>()