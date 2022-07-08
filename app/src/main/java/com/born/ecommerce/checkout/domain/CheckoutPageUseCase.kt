package com.born.ecommerce.checkout.domain

import com.born.ecommerce.account.data.AccountRepository
import com.born.ecommerce.checkout.data.CheckoutRepository
import com.born.ecommerce.domain.UseCase
import com.born.ecommerce.presentation.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

 class CheckoutPageUseCase @Inject constructor(
    private val checkoutRepository: CheckoutRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Any?>(dispatcher) {

     override suspend fun execute(parameters: Unit): Any? {
        return checkoutRepository.sampleApi()
     }

 }
