package com.android.models

data class Order(
    val orderId: String,

    val customerId: String,

    val orderNumber: String = "",

    val paymentId: String = "",

    val orderDate: Long,

    val shipDate: Long?,

    val requiredDate: Long?,

    val shipperId: String?,

    val freight: String?,

    val salesTax: Double?,

    val timestamp: Long,

    val transactionStatus: String,

    val errLoc: String?,

    val errMessage: String?,

    val fulfilled: Boolean,

    val deleted: Boolean?,

    val paid: Boolean,

    val paymentDate: Long,
)
