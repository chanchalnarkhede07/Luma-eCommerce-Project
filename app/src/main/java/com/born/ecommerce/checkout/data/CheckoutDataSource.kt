package com.born.ecommerce.checkout.data


import com.born.ecommerce.data.EcomEndPoint
import javax.inject.Inject
import javax.inject.Singleton

interface CheckoutDataSource {
    suspend fun sampleApi(): Any?
}

@Singleton
class CheckoutRemoteDataSource @Inject constructor(
    private val ecomEndPoint: EcomEndPoint
) : CheckoutDataSource {
    override suspend fun sampleApi(): Any? {
        return ecomEndPoint.sampleEndPoint()
    }
}