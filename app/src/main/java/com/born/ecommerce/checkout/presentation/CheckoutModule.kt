package com.born.ecommerce.checkout.presentation

import androidx.lifecycle.ViewModel
import com.born.ecommerce.presentation.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CheckoutModule {

    @ContributesAndroidInjector
    abstract fun contributeCheckoutFragment(): CheckoutFragment

    @Binds
    @IntoMap
    @ViewModelKey(CheckoutViewModel::class)
    abstract fun bindCheckoutFragmentViewModel(viewModel: CheckoutViewModel): ViewModel
}