package com.android.adhiyoz.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.adhiyoz.widget.SpaceItemDecoration
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Loads image from url into given image view using Glide library
 */
@BindingAdapter("loadWithGlide")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView)
        .load(url)
//        .error(R.drawable.glide_placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("app:customGridSpacing")
fun gridRecyclerViewSpacing(
    view: RecyclerView,
    space: Int
) {
    val spaceInPixel = space.convertDpToPixel()
    view.addItemDecoration(SpaceItemDecoration(3, spaceInPixel, true))
}