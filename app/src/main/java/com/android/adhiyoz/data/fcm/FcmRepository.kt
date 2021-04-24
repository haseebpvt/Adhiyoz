package com.android.adhiyoz.data.fcm

import javax.inject.Inject

interface FcmRepository {
    suspend fun sendCloudMessage(fcmRequest: FcmRequest)
}

class DefaultFcmRepository @Inject constructor(
    private val service: FcmService
) : FcmRepository {

    override suspend fun sendCloudMessage(fcmRequest: FcmRequest) {
        service.sendCloudMessage(fcmRequest)
    }
}