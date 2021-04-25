package com.android.adhiyoz.data.order

import com.android.models.Order
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

interface OrderRepository {
    fun placeOrder(order: Order): DocumentReference?
}

class DefaultOrderRepository @Inject constructor(
    private val orderDataSource: OrderDataSource
) : OrderRepository {

    override fun placeOrder(order: Order): DocumentReference? {
        return orderDataSource.placeOrder(order)
    }
}