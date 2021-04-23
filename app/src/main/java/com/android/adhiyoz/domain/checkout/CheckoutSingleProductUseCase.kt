package com.android.adhiyoz.domain.checkout

import com.android.adhiyoz.data.order.OrderRepository
import com.android.adhiyoz.di.IoDispatcher
import com.android.adhiyoz.result.Result
import com.android.models.Order
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckoutSingleProductUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(order: Order): Result<Unit> = withContext(dispatcher) {
        val result = try {
            orderRepository.placeOrder(order)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}