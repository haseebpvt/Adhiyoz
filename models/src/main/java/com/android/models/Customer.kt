package com.android.models

data class Customer(
    val customerId: String,

    val firstName: String,

    val lastName: String,

    val customerClass: String,

    val room: String,

    val building: String,

    val address1: String,

    val address2: String,

    val city: String,

    val state: String,

    val postalCode: String,

    val country: String,

    val phone: String,

    val email: String,

    val password: String,

    val billingAddress: String,

    val billingCity: String,

    val billingRegion: String,

    val billingPostalCode: String,

    val billingCountry: String,

    val shippingAddress: String,

    val shipCity: String,

    val shipRegion: String,

    val shipPostalCode: String,

    val shipCountry: String,

    val dateEntered: Long,
)
