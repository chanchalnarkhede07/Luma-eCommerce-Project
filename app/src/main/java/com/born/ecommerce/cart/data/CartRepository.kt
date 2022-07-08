package com.born.ecommerce.cart.data

import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

interface CartRepository {
    suspend fun sampleApi(): Any?
}

@Singleton
class DefaultCartRepository @Inject constructor(
    private val cartDataSource: CartDataSource
) : CartRepository {

    override suspend fun sampleApi(): Any? {
        return cartDataSource.sampleApi()
    }
}
