package com.born.ecommerce.category.view.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.born.ecommerce.R
import com.born.ecommerce.category.networking.LumaMVVMApi
import com.born.ecommerce.category.repository.MainRepositoryImpl
import com.born.ecommerce.category.view.adapter.ProductsAdapter
import com.born.ecommerce.category.view.state.ViewState
import com.born.ecommerce.category.viewmodel.MainViewModel
import com.born.ecommerce.category.viewmodel.MainViewModelProviderFactory
import com.born.ecommerce.databinding.FragmentProductList1Binding
import com.born.ecommerce.graphql.SearchProductQuery


class ProductList1Fragment : Fragment() {


    //binding variables
    private lateinit var binding: FragmentProductList1Binding

    //variables for recyclerview dataset
    private val productsAdapter by lazy { ProductsAdapter() }
    lateinit var layoutManager: LinearLayoutManager
    //lateinit var results: List<SearchProductQuery.Item?>
    var results = arrayListOf<SearchProductQuery.Item?>()

    private val lumaMVVMApi: LumaMVVMApi = LumaMVVMApi()
    private lateinit var viewModel: MainViewModel


    private var pageSize: Int = 100


    //variables required for scrolling logic
    lateinit var searchQuery: String
    var currenItem: Int = 0
    var totalItem = 0
    var scrolledItem = 0
    var pageNum = 1
    var totalPage: Int = 0
    private var totalItems: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchQuery = this.arguments?.getString("key").toString()
        //inflate the layout and initialise the binding object
        binding = FragmentProductList1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewmodel object initialise
        viewModel = ViewModelProvider(
            this,
            MainViewModelProviderFactory(MainRepositoryImpl(lumaMVVMApi))
        ).get(MainViewModel::class.java)

        //initialise the layout object to linear layout and set adapter and
        // layout to recylerview
        layoutManager = LinearLayoutManager(context)
        binding.productsRv.adapter = productsAdapter
        binding.productsRv.layoutManager = layoutManager

        Log.d("TAG", "In Before Query Search")
        viewModel.querySearchProductsList(searchQuery, pageNum)
        Log.d("TAG", "In After Query Search")
        observeLiveData()

        //Grid|List button on click functionality
        viewModel.isChecked.observe(viewLifecycleOwner) {
            binding.btnLayoutChange.setOnCheckedChangeListener { view, isChecked ->
                Log.d("TAG", "$isChecked")
                if (isChecked) {
                    binding.productsRv.layoutManager =
                        LinearLayoutManager(context)
                    viewModel.changeChecked()
                } else {
                    binding.productsRv.layoutManager =
                        GridLayoutManager(context, 2)
                }
            }
        }

        infiniteScroll()

        //On click product functionality
        productsAdapter.onItemClicked = { product ->

            product.let {
                if (!product.sku.isNullOrBlank()) {
                    findNavController().navigate(
                        ProductList1FragmentDirections.actionProductList1FragmentToProductdetailfragments()
                    )
                    Log.d("TAG", "$product")
                    Toast.makeText(context, "You Clicked On ${product.name}", Toast.LENGTH_LONG)
                        .show()
                }
            }

        }

        //Add to Cart Button Functionality
        productsAdapter.onCartButtonClicked = { product ->
            product.let {
                if (!product.sku.isNullOrBlank()) {
                    val cartId = "DjEAlU2ZOTlhreSdNaARM3CyynCMn9sD"
                    viewModel.addToCartButton(cartId, product.sku)
                    Log.d("TAG", "$product")
                }
                addToCartObesrveData()
            }
        }

        //sorting logic from here
        //getting layout varibales
        val sortContainerBackLL = binding.sortContainerBackLL
        val sortContainerLL = binding.sortContainerLL

        //setting animation to backlayout
        sortContainerBackLL.setOnClickListener {
            sortContainerLL.animate()
                .translationY(-sortContainerLL.getHeight().toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        sortContainerLL.animate().setListener(null)
                        sortContainerBackLL.setVisibility(View.GONE)
                    }
                })
        }
        //sort button on click logic
        binding.sortB.setOnClickListener {
            sortContainerBackLL.visibility = View.VISIBLE
            sortContainerLL.animate().translationY(0f)
        }
        binding.sortRG.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.sortByName -> {
                    val sort = results.sortedBy {
                        it?.name
                    }
                    productsAdapter.submitList(sort)
                    binding.productsRv.smoothScrollToPosition(0)
                }
                R.id.sortByReview -> {
                    val sort = results.sortedBy {
                        it?.review_count
                    }
                    productsAdapter.submitList(sort)
                    binding.productsRv.smoothScrollToPosition(0)
                }
                R.id.sortByPrice -> {
                    val sort = results.sortedBy {
                        it?.price_range?.minimum_price?.final_price?.value
                    }
                    productsAdapter.submitList(sort)
                    binding.productsRv.smoothScrollToPosition(0)
                }
                R.id.sortByReviewDsc -> {
                    val sort = results.sortedByDescending {
                        it?.review_count
                    }
                    productsAdapter.submitList(sort)
                    binding.productsRv.smoothScrollToPosition(0)
                }
                R.id.sortByNameDsc -> {
                    val sort = results.sortedByDescending {
                        it?.name
                    }
                    productsAdapter.submitList(sort)
                    binding.productsRv.smoothScrollToPosition(0)
                }
                R.id.sortByPriceDsc -> {
                    val sort = results.sortedByDescending {
                        it?.price_range?.minimum_price?.final_price?.value
                    }
                    productsAdapter.submitList(sort)
                    binding.productsRv.smoothScrollToPosition(0)
                }
            }
            sortContainerLL.animate()
                .translationY(-sortContainerLL.height.toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        sortContainerLL.animate().setListener(null)
                        sortContainerBackLL.visibility = View.GONE
                    }
                })
        }
    }

    //infinite scroll function
    private fun infiniteScroll() {
        binding.productsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currenItem = layoutManager.childCount
                scrolledItem = layoutManager.findLastVisibleItemPosition()
                totalItem = layoutManager.itemCount
                Log.d("TAG", "Current=$currenItem")
                Log.d("TAG", "Scrolled Item=$scrolledItem")
                Log.d("TAG", "TotalItem=$totalItem")
                Log.d("TAG", "Total Count=$totalItems")
                Log.d("TAG", "Current Page $pageNum")

                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    if (pageNum < totalPage && scrolledItem == totalItem - 1) {
                        pageNum++
                        viewModel.querySearchProductsList(searchQuery, pageNum)
                    }
                }
            }
        })
    }

    private fun addToCartObesrveData() {
        Log.d("TAG", "In Add to Cart ObserveDara")
        viewModel.cartList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.productFetchProgress.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.productFetchProgress.visibility = View.GONE
                    Toast.makeText(
                        context,
                        "Total Items In Cart + ${response.value?.data?.addSimpleProductsToCart?.cart?.items?.size}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "Error Message + ${response.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun observeLiveData() {
        Log.d("TAG", "In Observdata")
        viewModel.productsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.productsRv.visibility = View.GONE
                    binding.productFetchProgress.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    if (response.value?.data?.products?.items?.size == 0) {
                        productsAdapter.submitList(emptyList())
                        binding.productFetchProgress.visibility = View.GONE
                        binding.productsRv.visibility = View.GONE
                        binding.charactersEmptyText.visibility = View.VISIBLE
                    } else {
                        binding.productsRv.visibility = View.VISIBLE
                        binding.charactersEmptyText.visibility = View.GONE
                    }
                    if (pageNum == 1) {
                        totalItems = response.value?.data?.products?.total_count!!
                        totalPage = response.value?.data?.products?.page_info?.total_pages!!
                        results.clear()
                    }
                    results.addAll(response.value?.data?.products?.items!!)
                    Log.d("TAG", "$results")
                    // results = response.value?.data?.products?.items!!
                    productsAdapter.submitList(results)
                    binding.productFetchProgress.visibility = View.GONE
                }
                is ViewState.Error -> {
                    productsAdapter.submitList(emptyList())
                    binding.productFetchProgress.visibility = View.GONE
                    binding.productsRv.visibility = View.GONE
                    binding.charactersEmptyText.visibility = View.VISIBLE
                }
            }
        }
    }

}