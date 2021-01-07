package com.android.models

data class OrderDetails(
    val orderId: String,

    val productId: String,

    val orderNumber: String,

    val price: Double,

    val quantity: Int,

    val discount: Double,

    val total: Double,

    /**
     * Stock-keeping unit ID
     */
    val idSku: String,

    val size: Double,

    val color: String,

    /**
     * Represents whether the product is delivered or not
     */
    val fulfilled: Boolean,

    /**
     * Date of shipping
     */
    val shipDate: Long,

    /**
     * ID of order details
     */
    val orderDetailId: String,

    /**
     * Date of payment made
     */
    val billDate: Long
)
