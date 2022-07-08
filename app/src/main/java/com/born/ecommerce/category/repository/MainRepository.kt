package com.born.ecommerce.category.repository

import com.apollographql.apollo.api.Response
import com.born.ecommerce.graphql.AddToCartMutation
import com.born.ecommerce.graphql.CategoryProductQuery
import com.born.ecommerce.graphql.SearchProductQuery

interface MainRepository {

    //search product api call method required parameters searchQuery and currentPAGE
    suspend fun getSearchProductList(
        search: String,
        currentPage: Int
    ): Response<SearchProductQuery.Data>


    //category wise product api call method required parameters categoryID and currentPAGE
    suspend fun getCategoryProductList(
        catId: String,
        currentPage: Int
    ): Response<CategoryProductQuery.Data>

    //add product to cart
    suspend fun addToCart(
        cartId: String,
        sku: String
    ): Response<AddToCartMutation.Data>


}