package com.android.shared.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class IoDispatcher

@InstallIn(ApplicationComponent::class)
@Module
object CoroutineModule {

    @IoDispatcher
    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}