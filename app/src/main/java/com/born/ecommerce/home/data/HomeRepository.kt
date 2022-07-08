package com.born.ecommerce.home.data

import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

interface HomeRepository {
    suspend fun getHomePageApi(signInParam: SignInParam): Any?
}

@Singleton
class DefaultHomeRepository @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {

    override suspend fun getHomePageApi(signInParam: SignInParam): Any? {
        return homeDataSource.getHomePageApi(signInParam)
    }
}

data class SignInParam(
    @SerializedName("username") val username : String,
    @SerializedName("password") val password : String
)