package com.born.ecommerce.presentation.di.module


import android.content.Context
import com.born.ecommerce.EcommerceApp
import com.born.ecommerce.account.data.AccountDataSource
import com.born.ecommerce.account.data.AccountRemoteDataSource
import com.born.ecommerce.account.data.AccountRepository
import com.born.ecommerce.account.data.DefaultAccountRepository
import com.born.ecommerce.cart.data.CartDataSource
import com.born.ecommerce.cart.data.CartRemoteDataSource
import com.born.ecommerce.cart.data.CartRepository
import com.born.ecommerce.cart.data.DefaultCartRepository
import com.born.ecommerce.checkout.data.CheckoutDataSource
import com.born.ecommerce.checkout.data.CheckoutRemoteDataSource
import com.born.ecommerce.checkout.data.CheckoutRepository
import com.born.ecommerce.checkout.data.DefaultCheckoutRepository
import com.born.ecommerce.data.EcomDataSource
import com.born.ecommerce.data.EcomEndPoint
import com.born.ecommerce.data.RemoteEcomDataSource
import com.born.ecommerce.data.db.EcomAppDatabase
import com.born.ecommerce.data.pref.PreferenceStorage
import com.born.ecommerce.data.pref.SharedPreferenceStorage
import com.born.ecommerce.home.data.DefaultHomeRepository
import com.born.ecommerce.home.data.HomeDataSource
import com.born.ecommerce.home.data.HomeRemoteDataSource
import com.born.ecommerce.home.data.HomeRepository
import com.born.ecommerce.product.data.DefaultProductRepository
import com.born.ecommerce.product.data.ProductDataSource
import com.born.ecommerce.product.data.ProductRemoteDataSource
import com.born.ecommerce.product.data.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module
 */
@Module
object AppModule {

    /**
     * application context are provided
     */
    @Provides
    fun provideApplicationContext(application: EcommerceApp): Context {
        return application.applicationContext
    }

    /**
     * AppDatabase instance are provided
     */
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): EcomAppDatabase {
        return EcomAppDatabase.buildDatabase(context)
    }

    @Singleton
    @Provides
    fun providePreferenceStorage(context: Context): PreferenceStorage {
        return SharedPreferenceStorage(context)
    }

    @Singleton
    @Provides
    fun provideGlobalAuthenticationDataSource(
        ecomEndPoint: EcomEndPoint
    ): AccountDataSource {
        return AccountRemoteDataSource(ecomEndPoint)
    }

    @Singleton
    @Provides
    fun provideGlobalAuthenticationRepository(
        accountDataSource: AccountDataSource
    ): AccountRepository {
        return DefaultAccountRepository(accountDataSource)
    }

    @Singleton
    @Provides
    fun provideProductDataSource(
        ecomEndPoint: EcomEndPoint
    ): ProductDataSource {
        return ProductRemoteDataSource(ecomEndPoint)
    }

    @Singleton
    @Provides
    fun provideProductRepository(
        productDataSource: ProductDataSource
    ): ProductRepository {
        return DefaultProductRepository(productDataSource)
    }


    @Singleton
    @Provides
    fun provideCartDataSource(
        ecomEndPoint: EcomEndPoint
    ): CartDataSource {
        return CartRemoteDataSource(ecomEndPoint)
    }

    @Singleton
    @Provides
    fun provideCartRepository(
        cartDataSource: CartDataSource
    ): CartRepository {
        return DefaultCartRepository(cartDataSource)
    }

    @Singleton
    @Provides
    fun provideCheckoutDataSource(
        ecomEndPoint: EcomEndPoint
    ): CheckoutDataSource {
        return CheckoutRemoteDataSource(ecomEndPoint)
    }

    @Singleton
    @Provides
    fun provideCheckoutRepository(
        checkoutDataSource: CheckoutDataSource
    ): CheckoutRepository {
        return DefaultCheckoutRepository(checkoutDataSource)
    }

    @Singleton
    @Provides
    fun provideAccountDataSource(
        ecomEndPoint: EcomEndPoint
    ): AccountDataSource {
        return AccountRemoteDataSource(ecomEndPoint)
    }

    @Singleton
    @Provides
    fun provideAccountRepository(
        accountDataSource: AccountDataSource
    ): AccountRepository {
        return DefaultAccountRepository(accountDataSource)
    }


  /*  @Singleton
    @Provides
    fun provideSearchDataSource(
        klevuEndPoint: KlevuEndPoint,
        appDatabase: AppDatabase
    ): SearchDataSource {
        return SearchRemoteDataSource(klevuEndPoint, appDatabase)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        searchDataSource: SearchDataSource,
        preferenceStorage: PreferenceStorage
    ): SearchRepository {
        return DefaultSearchRepository(searchDataSource, preferenceStorage)
    }*/

    @Singleton
    @Provides
    fun provideHomeDataSource(
        ecomEndPoint: EcomEndPoint
    ): HomeDataSource {
        return HomeRemoteDataSource(ecomEndPoint)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        homeDataSource: HomeDataSource
    ): HomeRepository {
        return DefaultHomeRepository(homeDataSource)
    }

}

