package com.android.adhiyoz.di

import com.android.adhiyoz.data.ServiceGenerator
import com.android.adhiyoz.data.fcm.FcmService
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideFriebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return ServiceGenerator.getRetrofitInstance()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): FcmService {
        return retrofit.create(FcmService::class.java)
    }
}