package com.born.ecommerce.cart.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.born.ecommerce.cart.domain.CartPageUseCase
import com.born.ecommerce.domain.result.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartPageUseCase: CartPageUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Cart Fragment"
    }
    val text: LiveData<String> = _text

    private val _cartPageSample = MutableLiveData<Any?>()
    val cartPageSample: LiveData<Any?>
        get() = _cartPageSample


    fun getCartSample() {
        viewModelScope.launch {
            when (val result = cartPageUseCase(Unit)) {
                is Result.Success -> _cartPageSample.value = result.data
                is Result.Error -> _cartPageSample.value = result.exception
                else -> {}
            }
        }
    }


}