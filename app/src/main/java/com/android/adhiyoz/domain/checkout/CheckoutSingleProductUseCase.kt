package com.android.adhiyoz.domain.checkout

import com.android.adhiyoz.constant.PaymentMethods
import com.android.adhiyoz.data.order.OrderRepository
import com.android.adhiyoz.di.IoDispatcher
import com.android.adhiyoz.result.Result
import com.android.models.Order
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class CheckoutSingleProductUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(customerId: String, paymentMethods: PaymentMethods): Result<Unit> =
        withContext(dispatcher) {
            val result = try {
                val order = if (paymentMethods == PaymentMethods.GOOGLE_PAY) {
                    Order(
                        customerId = customerId,
                        orderDate = Date().time,
                        timestamp = Date().time,
                        transactionStatus = "paid",
                        fulfilled = false,
                        paid = true,
                        paymentDate = Date().time
                    )
                } else {
                    Order(
                        customerId = customerId,
                        orderDate = Date().time,
                        timestamp = Date().time,
                        transactionStatus = "not_paid",
                        fulfilled = false,
                        paid = false,
                    )
                }

                orderRepository.placeOrder(order)
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }

            return@withContext Result.Success(result)
        }
}