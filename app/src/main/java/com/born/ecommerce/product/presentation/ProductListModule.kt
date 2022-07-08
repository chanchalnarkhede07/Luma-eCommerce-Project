package com.born.ecommerce.product.presentation

import androidx.lifecycle.ViewModel
import com.born.ecommerce.presentation.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ProductListModule {

    @ContributesAndroidInjector
    abstract fun contributeProductListFragment(): ProductListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    abstract fun bindProductListFragmentViewModel(viewModel: ProductListViewModel): ViewModel
}