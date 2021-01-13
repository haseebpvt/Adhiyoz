package com.android.models

data class Category(
    val categoryId: String,
    val categoryName: String,
    val description: String,
    val image: String,

    /**
     * Represents if the category is active now. Shouldn't show if the category is inactive
     */
    val active: Boolean
)