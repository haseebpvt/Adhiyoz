package com.android.adhiyoz.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.models.Category

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