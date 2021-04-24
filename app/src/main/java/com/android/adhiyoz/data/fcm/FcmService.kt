package com.android.adhiyoz.data.fcm

import retrofit2.http.Body
import retrofit2.http.POST

interface FcmService {

    @POST("send")
    suspend fun sendCloudMessage(@Body fcmRequest: FcmRequest)
}