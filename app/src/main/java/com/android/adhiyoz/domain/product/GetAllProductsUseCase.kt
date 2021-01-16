package com.android.adhiyoz.domain.product

import com.android.adhiyoz.data.product.ProductRepository
import com.android.adhiyoz.di.IoDispatcher
import com.android.adhiyoz.result.Result
import com.android.models.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Result<List<Product>> = withContext(dispatcher) {
        val result = try {
            productRepository.getAllProducts()
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}