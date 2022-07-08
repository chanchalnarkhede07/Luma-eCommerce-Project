package com.born.ecommerce.product.data

import javax.inject.Inject
import javax.inject.Singleton

interface ProductRepository {
    suspend fun sampleApi(): Any?
}

@Singleton
class DefaultProductRepository @Inject constructor(
    private val productDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun sampleApi(): Any? {
      return productDataSource.sampleApi()
    }
}
