package com.born.ecommerce.home.presenation

import androidx.lifecycle.ViewModel
import com.born.ecommerce.presentation.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel: HomeViewModel): ViewModel
}