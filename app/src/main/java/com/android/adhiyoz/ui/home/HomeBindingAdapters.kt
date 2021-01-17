package com.android.adhiyoz.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.models.Category
import com.android.models.Product

@BindingAdapter(value = ["homeCategoryItems", "viewModel"], requireAll = true)
fun homeCategoryItems(
    recyclerView: RecyclerView,
    items: List<Category>?,
    viewModel: HomeViewModel
) {
    items ?: return

    if (recyclerView.adapter == null) {
        recyclerView.adapter = HomeCategoryAdapter(viewModel)
    }

    (recyclerView.adapter as HomeCategoryAdapter).apply {
        submitList(items)
    }
}

@BindingAdapter(value = ["homeProductItems", "viewModel"], requireAll = true)
fun homeProductItems(
    recyclerView: RecyclerView,
    items: List<Product>?,
    viewModel: HomeViewModel
) {
    items ?: return

    if (recyclerView.adapter == null) {
        recyclerView.adapter = HomeTopProductAdapter(viewModel)
    }

    (recyclerView.adapter as HomeTopProductAdapter).apply {
        submitList(items)
    }
}