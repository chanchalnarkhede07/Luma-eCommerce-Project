package com.born.ecommerce.cart.data


import com.born.ecommerce.data.EcomEndPoint
import javax.inject.Inject
import javax.inject.Singleton

interface CartDataSource {
    suspend fun sampleApi(): Any?
}

@Singleton
class CartRemoteDataSource @Inject constructor(
    private val ecomEndPoint: EcomEndPoint
) : CartDataSource {
    override suspend fun sampleApi(): Any? {
        return ecomEndPoint.sampleEndPoint()
    }
}