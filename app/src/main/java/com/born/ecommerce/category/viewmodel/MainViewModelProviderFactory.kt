package com.born.ecommerce.category.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.born.ecommerce.category.repository.MainRepositoryImpl

class MainViewModelProviderFactory(val mainRepositoryImpl: MainRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.mainRepositoryImpl) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}