package com.born.ecommerce.presentation.di.module

import androidx.lifecycle.ViewModelProvider
import com.born.ecommerce.presentation.factory.EcomViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: EcomViewModelFactory): ViewModelProvider.Factory
}
