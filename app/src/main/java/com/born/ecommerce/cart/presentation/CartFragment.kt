package com.born.ecommerce.cart.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.born.ecommerce.databinding.FragmentNotificationsBinding
import javax.inject.Inject

class CartFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
  //  @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//    private lateinit var cartViewModel: CartViewModel


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val notificationsViewModel =
//            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
//                CartViewModel::class.java
//            )

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

//        cartViewModel.cartPageSample.observe(viewLifecycleOwner) {
//            Toast.makeText(context, it?.toString(), Toast.LENGTH_LONG).show()
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}