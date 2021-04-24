package com.android.adhiyoz.data.fcm

import com.google.gson.annotations.SerializedName


data class FcmRequest(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("to")
    val to: String
)

data class Data(
    @SerializedName("key1")
    val key1: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("title")
    val title: String
)