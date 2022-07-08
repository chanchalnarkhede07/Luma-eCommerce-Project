package com.born.ecommerce.presentation.di.module

import android.content.Context
import com.born.ecommerce.BuildConfig
import com.born.ecommerce.data.EcomEndPoint
import com.born.ecommerce.data.interceptor.AuthInterceptor
import com.born.ecommerce.data.interceptor.EcommerceInterceptor
import com.born.ecommerce.data.pref.PreferenceStorage
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * tells Dagger that this class is a dagger module
 * object makes more performance
 */
@Module
object NetworkModule {

    @Provides
    fun provideOkHttpClientBuilder() = OkHttpClient.Builder()

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()

    /**
     * @Provides tell Dagger how to create instances of the type that this function
     * returns (here OkHttpClient instance)
     * Function parameters are the dependencies of this type
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        context: Context,
        preferenceStorage: PreferenceStorage
    ): OkHttpClient {
        okHttpClientBuilder.addInterceptor(AuthInterceptor(preferenceStorage))
        okHttpClientBuilder.addInterceptor(EcommerceInterceptor())
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

    /**
     * @Provides tell Dagger how to create instances of the type that this function
     * returns (here Retrofit instance)
     * Function parameters are the dependencies of this type
     */
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideEcomEndpoint(retrofit: Retrofit): EcomEndPoint {
        return retrofit.create(EcomEndPoint::class.java)
    }
}
