package com.android.models

data class Order(
    val orderId: String? = "",

    val customerId: String,

    val orderNumber: String = "",

    val paymentId: String = "",

    val orderDate: Long,

    val shipDate: Long? = 0,

    val requiredDate: Long? = 0,

    val shipperId: String? = "",

    val freight: String? = "",

    val salesTax: Double? = 0.0,

    val timestamp: Long,

    val transactionStatus: String,

    val errLoc: String? = "",

    val errMessage: String? = "",

    val fulfilled: Boolean,

    val deleted: Boolean? = false,

    val paid: Boolean,

    val paymentDate: Long? = 0,
)
