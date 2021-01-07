package com.android.models.result

data class Category(
    val productId: String,
    val categoryName: String,
    val description: String,

    /**
     * Represents if the category is active now. Shouldn't show if the category is inactive
     */
    val active: Boolean
)