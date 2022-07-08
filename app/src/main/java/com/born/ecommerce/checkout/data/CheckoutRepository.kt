package com.born.ecommerce.checkout.data

import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

interface CheckoutRepository {
    suspend fun sampleApi(): Any?
}

@Singleton
class DefaultCheckoutRepository @Inject constructor(
    private val checkoutDataSource: CheckoutDataSource
) : CheckoutRepository {

    override suspend fun sampleApi(): Any? {
        return checkoutDataSource.sampleApi()
    }
}
