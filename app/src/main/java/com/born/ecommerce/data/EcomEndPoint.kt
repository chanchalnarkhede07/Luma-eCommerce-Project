package com.born.ecommerce.data

import com.born.ecommerce.home.data.SignInParam
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EcomEndPoint {

    @POST("integration/customer/token")
    suspend fun getCustomerAccessToken(@Body signInParam: SignInParam):String?

    @POST("integration/customer/token")
    suspend fun sampleEndPoint():Any?
}