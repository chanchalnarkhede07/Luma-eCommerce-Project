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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.born.ecommerce.R
import com.born.ecommerce.category.networking.LumaMVVMApi
import com.born.ecommerce.category.repository.MainRepositoryImpl
import com.born.ecommerce.category.view.adapter.CategoryProductsAdapter
import com.born.ecommerce.category.view.state.ViewState
import com.born.ecommerce.category.viewmodel.MainViewModel
import com.born.ecommerce.category.viewmodel.MainViewModelProviderFactory
import com.born.ecommerce.databinding.FragmentCategoryProductListBinding
import com.born.ecommerce.graphql.CategoryProductQuery


class CategoryProductListFragment : Fragment() {

    //binding variables
    private lateinit var binding: FragmentCategoryProductListBinding

    private val args: CategoryProductListFragmentArgs by navArgs()
    private val lumaMVVMApi: LumaMVVMApi = LumaMVVMApi()
    private lateinit var viewModel: MainViewModel

    //variables for recyclerview dataset
    private val productsAdapter by lazy { CategoryProductsAdapter() }
    private var results = arrayListOf<CategoryProductQuery.Item?>()
    lateinit var layoutManager: LinearLayoutManager


    //variables required for scrolling logic
    lateinit var catID: String
    private var totalItems: Int = -1
    private var currentItem: Int = 0
    private var totalItem = 0
    private var scrolledItem = 0
    private var pageNum = 1
    private var totalPage: Int = 0
    private var pageSize: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //assigning value for category id coming from prev fragments
        catID = this.args.id
        // Inflate the layout for this fragment
        binding = FragmentCategoryProductListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialise view model object
        viewModel = ViewModelProvider(
            this,
            MainViewModelProviderFactory(MainRepositoryImpl(lumaMVVMApi))
        ).get(MainViewModel::class.java)

        //initialise the layout object to linear layout and set adapter and
        // layout to recyclerview
        layoutManager = LinearLayoutManager(context)
        binding.productsRv.adapter = productsAdapter
        binding.productsRv.layoutManager = layoutManager

        //calling the api call for categoryQuery
        viewModel.queryCategoryProductsList(catID, pageNum)
        observeLiveData()

        //Grid-List functionality
        viewModel.isChecked.observe(viewLifecycleOwner) {
            binding.btnLayoutChange.setOnCheckedChangeListener { view, isChecked ->
                Log.d("TAG", "$isChecked")
                if (isChecked) {
                    binding.productsRv.layoutManager =
                        LinearLayoutManager(activity?.applicationContext)

                    viewModel.changeChecked()
                } else {
                    binding.productsRv.layoutManager =
                        GridLayoutManager(activity?.applicationContext, 2)
                }
            }
        }

        //Infinite scroll functionality
        infiniteScroll()

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
                R.id.sortByNameDsc -> {
                    val sort = results.sortedByDescending {
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
                R.id.sortByReviewDsc -> {
                    val sort = results.sortedByDescending {
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

        //on Click Product Functionality
        productsAdapter.onItemClicked = { product ->
            product.let {
                if (!product.sku.isNullOrBlank()) {
                    findNavController().navigate(
                        CategoryProductListFragmentDirections.actionCategoryProductListFragmentToProductdetailfragments()
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
                addToCartObserveData()
            }
        }

    }

    private fun infiniteScroll() {
        binding.productsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = layoutManager.childCount
                scrolledItem = layoutManager.findLastVisibleItemPosition()
                totalItem = layoutManager.itemCount
                Log.d("TAG", "Current=$currentItem")
                Log.d("TAG", "Scrolled Item=$scrolledItem")
                Log.d("TAG", "TotalItem=$totalItem")
                Log.d("TAG", "Total Count=$totalItems")
                Log.d("TAG", "Current Page $pageNum")
                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    if (pageNum < totalPage && scrolledItem == totalItem - 1) {
                        pageNum++
                        viewModel.queryCategoryProductsList(catID, pageNum)
                    }
                }
            }
        })
    }

    private fun observeLiveData() {
        viewModel.catProductsList.observe(viewLifecycleOwner) { response ->
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

    private fun addToCartObserveData() {
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

}