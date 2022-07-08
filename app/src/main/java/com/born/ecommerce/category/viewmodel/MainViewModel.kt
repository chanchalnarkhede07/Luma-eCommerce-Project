package com.born.ecommerce.category.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.born.ecommerce.category.repository.MainRepository
import com.born.ecommerce.category.view.state.ViewState
import com.born.ecommerce.graphql.AddToCartMutation
import com.born.ecommerce.graphql.CategoryProductQuery
import com.born.ecommerce.graphql.SearchProductQuery
import kotlinx.coroutines.launch

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {


    private val _productsList by lazy { MutableLiveData<ViewState<Response<SearchProductQuery.Data>>>() }
    val productsList: LiveData<ViewState<Response<SearchProductQuery.Data>>>
        get() = _productsList

    fun querySearchProductsList(name: String, pageNum: Int) = viewModelScope.launch {
        _productsList.postValue(ViewState.Loading())
        try {
            val response = mainRepository.getSearchProductList(name, pageNum)
            Log.d("TAG", "${response}")
            Log.d("TAG", "In Viewmdoel")
            _productsList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _productsList.postValue(ViewState.Error("Error fetching products"))
        }
    }

    private val _catProductsList by lazy { MutableLiveData<ViewState<Response<CategoryProductQuery.Data>>>() }
    val catProductsList: LiveData<ViewState<Response<CategoryProductQuery.Data>>>
        get() = _catProductsList

    fun queryCategoryProductsList(catId: String, pageNum: Int) = viewModelScope.launch {
        _catProductsList.postValue(ViewState.Loading())
        try {
            val response = mainRepository.getCategoryProductList(catId, pageNum)
            Log.d("TAG", "${response}")
            _catProductsList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _productsList.postValue(ViewState.Error("Error fetching products"))
        }
    }

    private val _cartList by lazy { MutableLiveData<ViewState<Response<AddToCartMutation.Data>>>() }
    val cartList: LiveData<ViewState<Response<AddToCartMutation.Data>>>
        get() = _cartList

    fun addToCartButton(cartId: String,sku:String) = viewModelScope.launch {
        _cartList.postValue(ViewState.Loading())
        try {
            val response = mainRepository.addToCart(cartId,sku)
            Log.d("TAG", "${response}")
            _cartList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _cartList.postValue(ViewState.Error("Error fetching products"))
        }
    }

    private val _isChecked = MutableLiveData<Boolean>(true)
    val isChecked: LiveData<Boolean>
        get() = _isChecked

    fun changeChecked() {
        _isChecked.value = false
    }


}