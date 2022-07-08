package com.born.ecommerce.data.interceptor

import com.born.ecommerce.data.utils.Constants
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class EcommerceInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val url = chain.request().url.toString()

        val baseUrl = Constants.BASE_URL
        if (url.contains(baseUrl)) {
            val path = url.substring(baseUrl.length)
            val httpUrl: HttpUrl? = "$baseUrl/rest/V1/$path".toHttpUrlOrNull()
            if (httpUrl != null) {
                requestBuilder.url(httpUrl)
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}