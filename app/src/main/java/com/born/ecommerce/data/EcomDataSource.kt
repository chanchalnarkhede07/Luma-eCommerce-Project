package com.born.ecommerce.data

import javax.inject.Inject

interface EcomDataSource {
    suspend fun getEcomData(): Any?
}

class RemoteEcomDataSource @Inject constructor(
    private val ecomEndPoint: EcomEndPoint
) : EcomDataSource {
    override suspend fun getEcomData(): Any? {
        return ecomEndPoint.sampleEndPoint()
    }
}