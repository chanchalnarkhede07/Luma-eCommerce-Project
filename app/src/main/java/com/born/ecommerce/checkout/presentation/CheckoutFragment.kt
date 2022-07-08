package com.born.ecommerce.checkout.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.born.ecommerce.R
import com.born.ecommerce.cart.presentation.CartViewModel
import com.born.ecommerce.databinding.CheckoutFragmentBinding
import com.born.ecommerce.databinding.FragmentNotificationsBinding


class CheckoutFragment : Fragment() {

    private var _binding: CheckoutFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                CartViewModel::class.java
            )

        _binding = CheckoutFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.message
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}