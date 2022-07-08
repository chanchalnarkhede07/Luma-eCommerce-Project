package com.born.ecommerce.data.interceptor

import com.born.ecommerce.data.pref.PreferenceStorage
import com.born.ecommerce.data.utils.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val headerName = "Authorization"
        val originalRequest = chain.request().newBuilder()
            .addHeader(headerName, setAuthHeader())
            .build()
        return chain.proceed(originalRequest)
    }

    private fun setAuthHeader(): String {
        return "Bearer ${preferenceStorage.userToken}"
    }
}