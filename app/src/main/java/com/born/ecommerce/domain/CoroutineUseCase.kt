package com.born.ecommerce.domain

import android.util.Log
import com.born.ecommerce.domain.result.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {


    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (ex: Exception) {

            if (ex !is CancellationException) {
               Log.e("CourtineLevelException",ex.message.toString())
            }
            Result.Error(ex)
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}
