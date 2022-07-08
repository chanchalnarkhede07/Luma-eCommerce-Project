package com.born.ecommerce.product.data


import com.born.ecommerce.data.EcomEndPoint
import javax.inject.Inject
import javax.inject.Singleton

interface ProductDataSource {
    suspend fun sampleApi(): Any?
}

@Singleton
class ProductRemoteDataSource @Inject constructor(
    private val ecomEndPoint: EcomEndPoint
) : ProductDataSource {
    override suspend fun sampleApi(): Any? {
        return ecomEndPoint.sampleEndPoint()
    }
}