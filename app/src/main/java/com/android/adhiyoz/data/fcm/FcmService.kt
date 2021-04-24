package com.android.adhiyoz.data.fcm

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FcmService {

    companion object {
        private const val SERVER_KEY =
            "AAAAyRriC1c:APA91bH01yAHs3z9-zS2z2cWR9Bwj_mQ1OJ2a2UArP2ay7GP-ryvIYbEiS_X9pGzjJsfnABB6HjRhT6mySqkkWo0Ak8iyLVwHS8VzfDyeT5964QCnoomu1PaD3qjOuDwoII1Uz4Knypu"
    }

    @Headers(
        "Authorization: key=$SERVER_KEY",
        "Content-Type:application/json"
    )
    @POST("send")
    suspend fun sendCloudMessage(@Body fcmRequest: FcmRequest)
}