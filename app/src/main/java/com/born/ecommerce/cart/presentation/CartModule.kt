package com.born.ecommerce.cart.presentation

import androidx.lifecycle.ViewModel
import com.born.ecommerce.presentation.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CartModule {

    @ContributesAndroidInjector
    abstract fun contributeCartFragment(): CartFragment

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindAddtoCartViewModel(viewModel: CartViewModel): ViewModel

}