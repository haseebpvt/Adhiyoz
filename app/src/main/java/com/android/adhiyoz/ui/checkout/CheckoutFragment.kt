package com.android.adhiyoz.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.adhiyoz.databinding.FragmentCheckoutBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private val viewModel: CheckoutViewModel by viewModels()

    private val args: CheckoutFragmentArgs by navArgs()

    private lateinit var binding: FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = this@CheckoutFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadProductDetails(productId = args.productId)

        FirebaseAuth.getInstance().currentUser?.uid?.let {
            viewModel.loadCustomerDetails(userId = it)
        }
    }
}