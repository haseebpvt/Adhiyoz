package com.android.adhiyoz.ui.home

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.android.adhiyoz.R
import com.android.adhiyoz.databinding.HomeFragmentBinding
import com.android.adhiyoz.result.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater).apply {
            viewmodel = viewModel
            lifecycleOwner = this@HomeFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verticalDivider =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        verticalDivider.setDrawable(ColorDrawable(resources.getColor(R.color.divider_grey)))

        val horizontalDivider =
            DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        horizontalDivider.setDrawable(ColorDrawable(resources.getColor(R.color.divider_grey)))

        binding.topProductsRecyclerView.addItemDecoration(verticalDivider)
        binding.topProductsRecyclerView.addItemDecoration(horizontalDivider)

        // Action
        viewModel.actionProductDetails.observe(viewLifecycleOwner, EventObserver { productId ->
            val action = HomeFragmentDirections
                .actionNavigationHomeToProductDetailsFragment(productId)
            findNavController().navigate(action)
        })
    }
}