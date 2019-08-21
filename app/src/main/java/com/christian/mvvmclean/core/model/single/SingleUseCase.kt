package com.christian.mvvmclean.core.model.single

import com.christian.mvvmclean.core.model.base.BaseUseCase
import com.christian.mvvmclean.core.model.base.BaseUseCaseX


@Suppress("UNCHECKED_CAST")
abstract class SingleUseCase<T, in Params>: BaseUseCaseX<T, Params>()