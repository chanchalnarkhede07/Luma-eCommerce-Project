package com.born.ecommerce.home.data


import com.born.ecommerce.data.EcomEndPoint
import javax.inject.Inject
import javax.inject.Singleton

interface HomeDataSource {
    suspend fun getHomePageApi(signInParam: SignInParam): Any?
}

@Singleton
class HomeRemoteDataSource @Inject constructor(
    private val ecomEndPoint: EcomEndPoint
) : HomeDataSource {
    override suspend fun getHomePageApi(signInParam: SignInParam): Any? {
        return ecomEndPoint.getCustomerAccessToken(signInParam)
    }
}