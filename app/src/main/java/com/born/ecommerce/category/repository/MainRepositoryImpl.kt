package  com.born.ecommerce.category.repository

import android.util.Log
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.born.ecommerce.category.networking.LumaMVVMApi
import com.born.ecommerce.graphql.AddToCartMutation
import com.born.ecommerce.graphql.CategoryProductQuery
import com.born.ecommerce.graphql.SearchProductQuery

class MainRepositoryImpl constructor(private val lumaMVVMApi: LumaMVVMApi) : MainRepository {


    override suspend fun getSearchProductList(
        search: String,
        currentPage: Int
    ): Response<SearchProductQuery.Data> {
        Log.d("TAG", "In MAinRepoImpl getSearchProductList Method")
        return lumaMVVMApi.getApolloClient().query(SearchProductQuery(search, currentPage)).await()
    }

    override suspend fun getCategoryProductList(
        catId: String,
        currentPage: Int
    ): Response<CategoryProductQuery.Data> {
        Log.d("TAG", "In MAinRepoImpl getCategoryProductList Method")
        return lumaMVVMApi.getApolloClient().query(CategoryProductQuery(catId, currentPage)).await()
    }

    override suspend fun addToCart(
        cartId: String,
        sku: String
    ): Response<AddToCartMutation.Data> {
        Log.d("TAG", "In MAinRepoImpl getCategoryProductList Method")
        return lumaMVVMApi.getApolloClient().mutate(AddToCartMutation(cartId,sku)).await()
    }

    //awiat method suspends the apollocall until it completed or returns and error on failure
}