package com.born.ecommerce.category.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.born.ecommerce.category.model.CategoryDataClass
import com.born.ecommerce.category.view.adapter.CategoryListAdapter
import com.born.ecommerce.databinding.FragmentCategoryBinding

//fragments for category class
class CategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //creating the list for category
        val catList = arrayListOf<CategoryDataClass>()
        catList.add(
            CategoryDataClass(
                "MAN",
                "https://2-4-latest-xauwnsa-k6a3y7n5cxo3u.us-4.magentosite.cloud/media/catalog/product/cache/685e4307dfd5a49099a8bd84de0f76e0/m/j/mj10-red_main_1.jpg"
            )
        )
        catList.add(
            CategoryDataClass(
                "WOMEN",
                "https://2-4-latest-xauwnsa-k6a3y7n5cxo3u.us-4.magentosite.cloud/media/catalog/product/cache/685e4307dfd5a49099a8bd84de0f76e0/w/s/ws05-black_main_1.jpg"
            )
        )
        catList.add(
            CategoryDataClass(
                "GEAR",
                "https://2-4-latest-xauwnsa-k6a3y7n5cxo3u.us-4.magentosite.cloud/media/catalog/product/cache/685e4307dfd5a49099a8bd84de0f76e0/m/g/mg05-br-0.jpg"
            )
        )
        catList.add(
            CategoryDataClass(
                "ELECTRONICS",
                "https://2-4-latest-xauwnsa-k6a3y7n5cxo3u.us-4.magentosite.cloud/media/catalog/product/cache/685e4307dfd5a49099a8bd84de0f76e0/a/c/acer-laptop.jpg"
            )
        )

        val categoryAdapter: CategoryListAdapter? = context?.let { CategoryListAdapter(it, catList) }
        val categoryRecylerView: RecyclerView = binding.rvCategory
        categoryRecylerView.adapter = categoryAdapter
        categoryRecylerView.layoutManager = LinearLayoutManager(context)
    }
}