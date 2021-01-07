package com.android.models.result

data class Payment(
    val paymentId: String,

    val paymentType: String,

    /**
     * Represents weather this payment method is allowed in the app currently
     */
    val allowed: Boolean
)
