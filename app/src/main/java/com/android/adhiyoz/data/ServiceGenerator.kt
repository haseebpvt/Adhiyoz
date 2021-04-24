package com.android.adhiyoz.data

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object {
        private const val FIREBASE_CLOUD_MESSAGING_BASE_URL =
            "https://fcm.googleapis.com/fcm/"

        fun getRetrofitInstance(): Retrofit {
            val loggingInterceptor = HttpLoggingInterceptor { message ->
                Log.d("http_log", message)
            }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor(loggingInterceptor)

            return Retrofit.Builder()
                .baseUrl(FIREBASE_CLOUD_MESSAGING_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
        }
    }
}
