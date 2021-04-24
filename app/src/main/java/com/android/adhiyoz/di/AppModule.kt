package com.android.adhiyoz.di

import com.android.adhiyoz.data.category.CategoryDataSource
import com.android.adhiyoz.data.category.CategoryRepository
import com.android.adhiyoz.data.category.FirestoreCategoryDataSource
import com.android.adhiyoz.data.category.FirestoreCategoryRepository
import com.android.adhiyoz.data.customer.CustomerDataSource
import com.android.adhiyoz.data.customer.CustomerRepository
import com.android.adhiyoz.data.customer.FirestoreCustomerDataSource
import com.android.adhiyoz.data.customer.FirestoreCustomerRepository
import com.android.adhiyoz.data.fcm.DefaultFcmRepository
import com.android.adhiyoz.data.fcm.FcmRepository
import com.android.adhiyoz.data.fcm.FcmService
import com.android.adhiyoz.data.order.DefaultOrderDataSource
import com.android.adhiyoz.data.order.DefaultOrderRepository
import com.android.adhiyoz.data.order.OrderDataSource
import com.android.adhiyoz.data.order.OrderRepository
import com.android.adhiyoz.data.product.FirestoreProductDataSource
import com.android.adhiyoz.data.product.FirestoreProductRepository
import com.android.adhiyoz.data.product.ProductDataSource
import com.android.adhiyoz.data.product.ProductRepository
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

    @Singleton
    @Provides
    fun provideProductDataSource(firestore: FirebaseFirestore): ProductDataSource {
        return FirestoreProductDataSource(firestore)
    }

    @Singleton
    @Provides
    fun provideProductRepository(productDataSource: ProductDataSource): ProductRepository {
        return FirestoreProductRepository(productDataSource)
    }

    @Singleton
    @Provides
    fun provideCustomerDataSource(firestore: FirebaseFirestore): CustomerDataSource {
        return FirestoreCustomerDataSource(firestore)
    }

    @Singleton
    @Provides
    fun provideCustomerRepository(customerDataSource: CustomerDataSource): CustomerRepository {
        return FirestoreCustomerRepository(customerDataSource)
    }

    @Singleton
    @Provides
    fun provideOrderDataSource(firestore: FirebaseFirestore): OrderDataSource {
        return DefaultOrderDataSource(firestore)
    }

    @Singleton
    @Provides
    fun provideOrderRepository(orderDataSource: OrderDataSource): OrderRepository {
        return DefaultOrderRepository(orderDataSource)
    }

    @Singleton
    @Provides
    fun provideFcmRepository(fcmService: FcmService): FcmRepository {
        return DefaultFcmRepository(fcmService)
    }
}