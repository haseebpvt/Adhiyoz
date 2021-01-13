package com.android.adhiyoz.di

import com.android.adhiyoz.data.category.CategoryDataSource
import com.android.adhiyoz.data.category.CategoryRepository
import com.android.adhiyoz.data.category.FirestoreCategoryDataSource
import com.android.adhiyoz.data.category.FirestoreCategoryRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideCategoryDataSource(firestore: FirebaseFirestore): CategoryDataSource {
        return FirestoreCategoryDataSource(firestore)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(categoryDataSource: CategoryDataSource): CategoryRepository {
        return FirestoreCategoryRepository(categoryDataSource)
    }
}