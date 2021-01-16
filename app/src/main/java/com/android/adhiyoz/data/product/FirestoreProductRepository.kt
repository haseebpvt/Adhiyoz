package com.android.adhiyoz.data.product

import com.android.models.Product
import javax.inject.Inject

interface ProductRepository {
    fun getAllProducts(): List<Product>
}

class FirestoreProductRepository @Inject constructor(
    private val productDataSource: ProductDataSource
) : ProductRepository {

    override fun getAllProducts(): List<Product> {
        return productDataSource.getAllProducts()
    }
}