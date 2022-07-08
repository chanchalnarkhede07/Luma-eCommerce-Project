package com.born.ecommerce.product.domain

import com.born.ecommerce.domain.UseCase
import com.born.ecommerce.presentation.di.IoDispatcher
import com.born.ecommerce.home.data.HomeRepository
import com.born.ecommerce.home.data.SignInParam
import com.born.ecommerce.product.data.DefaultProductRepository
import com.born.ecommerce.product.data.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


open class ProductListPageUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Any?>(dispatcher) {
    override suspend fun execute(parameters: Unit): Any? {
        return productRepository.sampleApi()
    }
}
