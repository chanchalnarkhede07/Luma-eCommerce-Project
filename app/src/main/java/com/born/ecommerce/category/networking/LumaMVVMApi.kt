package com.born.ecommerce.category.networking

import android.os.Looper
import android.util.Log
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

//class for getting apollo client instances

class LumaMVVMApi {

    val id = "613e0trfv97lfbemu3x65n92xrwysgqy"
    val authHeader = "Bearer $id"

    fun getApolloClient(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Only the main thread can get the apolloClient instance"
        }

        Log.d("TAG", "In LumaMVVMAPi")
        val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder().method(original.method, original.body)
            builder.header("Authorization", authHeader)
            chain.proceed(builder.build())
        }
            .build()

        return ApolloClient.builder()
            .serverUrl("https://2-4-latest-xauwnsa-k6a3y7n5cxo3u.us-4.magentosite.cloud/graphql")
            .okHttpClient(okHttpClient)
            .build()


    }
}