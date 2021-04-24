package com.android.adhiyoz.domain.pushnotification

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject

class SendUpstreamMessageUseCase @Inject constructor() {

    suspend operator fun invoke(message: String) {
        sendUpstream(message)
    }

    private fun sendUpstream(message: String) {
        val SENDER_ID = "863739448151"
        val messageId = 0 // Increment for each
        val firebaseMessaging = FirebaseMessaging.getInstance()
        val message = RemoteMessage.Builder("$SENDER_ID@fcm.googleapis.com")
            .setMessageId(messageId.toString())
            .addData("my_message", message)
            .addData("my_action", "SAY_HELLO")
            .build()
        firebaseMessaging.send(message)
    }
}