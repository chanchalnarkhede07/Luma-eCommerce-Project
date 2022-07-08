package com.born.ecommerce.account.data


import com.born.ecommerce.data.EcomEndPoint
import javax.inject.Inject
import javax.inject.Singleton

interface AccountDataSource {
    suspend fun sampleApi(): Any?
}

@Singleton
class AccountRemoteDataSource @Inject constructor(
    private val ecomEndPoint: EcomEndPoint
) : AccountDataSource {
    override suspend fun sampleApi(): Any? {
        // git pracitse commit demo letar
        return ecomEndPoint.sampleEndPoint()
    }
}