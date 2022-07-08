package com.born.ecommerce.product.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.born.ecommerce.databinding.FragmentHomeBinding
import com.born.ecommerce.databinding.FragmentProductlistBinding

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(ProductListViewModel::class.java)

        _binding = FragmentProductlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProductList
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}