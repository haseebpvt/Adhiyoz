package com.android.adhiyoz.data.order

import com.android.models.Order
import javax.inject.Inject

interface OrderRepository {
    fun placeOrder(order: Order)
}

class DefaultOrderRepository @Inject constructor(
    private val orderDataSource: OrderDataSource
) : OrderRepository {

    override fun placeOrder(order: Order) {
        orderDataSource.placeOrder(order)
    }
}