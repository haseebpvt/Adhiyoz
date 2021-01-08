package com.android.shared.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideFriebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}