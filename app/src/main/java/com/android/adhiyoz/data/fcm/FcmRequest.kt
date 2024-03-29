package com.android.adhiyoz.data.fcm

import com.google.gson.annotations.SerializedName


data class FcmRequest(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("to")
    val to: String
)

data class Data(
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("priority")
    val priority: String = "high",
    @SerializedName("sound")
    val sound: String = "notification"
)