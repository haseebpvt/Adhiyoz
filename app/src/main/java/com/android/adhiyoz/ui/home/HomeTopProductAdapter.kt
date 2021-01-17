package com.android.adhiyoz.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.adhiyoz.databinding.ItemProductBinding
import com.android.models.Product

class HomeTopProductAdapter(
    private val viewModel: HomeViewModel
) : ListAdapter<Product, HomeTopProductAdapter.ProductViewHolder>(ProductDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }

    class ProductViewHolder constructor(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, viewModel: HomeViewModel) {
            binding.product = product
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
}