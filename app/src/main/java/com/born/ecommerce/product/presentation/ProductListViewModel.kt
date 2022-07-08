package com.born.ecommerce.product.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Product List Fragment"
    }
    val text: LiveData<String> = _text
}