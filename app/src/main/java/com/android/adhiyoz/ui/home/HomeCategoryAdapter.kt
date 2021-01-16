package com.android.adhiyoz.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.adhiyoz.databinding.ItemHomeCategoryBinding
import com.android.models.Category

class HomeCategoryAdapter(
    private val viewModel: HomeViewModel
) : ListAdapter<Category, HomeCategoryAdapter.CategoryViewHolder>(CategoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }

    class CategoryViewHolder(private val binding: ItemHomeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, viewModel: HomeViewModel) {
            binding.category = category
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }
    }

    class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.categoryId == newItem.categoryId
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}