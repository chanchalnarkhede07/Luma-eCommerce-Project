package com.born.ecommerce.account.presentation

import androidx.lifecycle.ViewModel
import com.born.ecommerce.presentation.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AccountModule {

    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): AccountFragment

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel

}