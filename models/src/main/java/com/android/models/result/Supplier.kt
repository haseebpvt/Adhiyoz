package com.android.models.result

data class Supplier(
    val supplierId: String,

    val companyName: String,

    val contactFName: String,

    val contactLName: String,

    val contactTitle: String,

    val address1: String,

    val address2: String,

    val city: String,

    val state: String,

    val postalCode: String,

    val country: String,

    val phone: String,

    val fax: String,

    val email: String,

    val url: String,

    val paymentMethod: String,

    val discountType: String,

    val typeGoods: String,

    val notes: String,

    val discountAvailable: Boolean,

    val currentOrder: String,

    val logo: String,

    val customerId: String,

    val sizeUrl: String,
)
