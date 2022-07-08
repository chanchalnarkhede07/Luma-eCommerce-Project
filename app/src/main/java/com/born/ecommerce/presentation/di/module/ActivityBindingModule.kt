package com.born.ecommerce.presentation.di.module

import com.born.ecommerce.account.presentation.AccountModule
import com.born.ecommerce.cart.presentation.CartModule
import com.born.ecommerce.checkout.presentation.CheckoutModule
import com.born.ecommerce.home.presenation.HomeModule
import com.born.ecommerce.presentation.MainActivity
import com.born.ecommerce.presentation.MainModule
import com.born.ecommerce.presentation.di.scope.ActivityScoped
import com.born.ecommerce.product.presentation.ProductListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            HomeModule::class,
            ProductListModule::class,
            AccountModule::class,
            CartModule::class,
            CheckoutModule::class
        ]
    )
    abstract fun mainActivity(): MainActivity
}