package com.born.ecommerce.account.domain

import com.born.ecommerce.account.data.AccountRepository
import com.born.ecommerce.domain.UseCase
import com.born.ecommerce.presentation.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

 class AccountPageUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Any?>(dispatcher) {

     override suspend fun execute(parameters: Unit): Any? {
        return accountRepository.sampleApi()
     }

 }
