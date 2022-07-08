package com.born.ecommerce.home.domain

import com.born.ecommerce.domain.UseCase
import com.born.ecommerce.presentation.di.IoDispatcher
import com.born.ecommerce.home.data.HomeRepository
import com.born.ecommerce.home.data.SignInParam
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


open class HomePageUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<SignInParam, Any?>(dispatcher) {
    override suspend fun execute(parameters: SignInParam): Any? {
        return homeRepository.getHomePageApi(parameters)
    }
}
