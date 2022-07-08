package com.born.ecommerce.account.data

import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

interface AccountRepository {
    suspend fun sampleApi(): Any?
}

@Singleton
class DefaultAccountRepository @Inject constructor(
    private val accountDataSource: AccountDataSource
) : AccountRepository {

    override suspend fun sampleApi(): Any? {
        return accountDataSource.sampleApi()
    }
}
