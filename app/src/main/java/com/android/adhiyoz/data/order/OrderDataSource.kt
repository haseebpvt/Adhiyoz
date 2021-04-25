package com.android.adhiyoz.data.order

import com.android.adhiyoz.data.customer.FirestoreCustomerDataSource
import com.android.models.Order
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface OrderDataSource {
    fun placeOrder(order: Order): DocumentReference?
}

class DefaultOrderDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : OrderDataSource {

    override fun placeOrder(order: Order): DocumentReference? {
        val task = firestore
            .collection(ORDER)
            .add(
                mapOf(
                    ORDER_ID to order.orderId,
                    CUSTOMER_ID to order.customerId,
                    ORDER_DATE to order.orderDate,
                    TIMESTAMP to order.timestamp,
                    TRANSACTION_STATUS to order.transactionStatus,
                    IS_FULFILLED to order.fulfilled,
                    IS_PAID to order.paid,
                    PAYMENT_DATE to order.paymentDate,
                )
            )

        return Tasks.await(task, 20, TimeUnit.SECONDS)
    }

    companion object {
        /**
         * Firestore constants for product
         */
        const val ORDER = "order"
        const val ORDER_ID = "orderId"
        const val CUSTOMER_ID = FirestoreCustomerDataSource.CUSTOMER_ID
        const val ORDER_NUMBER = "orderNumber"
        const val PAYMENT_ID = "paymentId"
        const val ORDER_DATE = "orderDate"
        const val SHIP_DATE = "shipDate"
        const val REQUIRED_DATE = "requiredDate"
        const val SHIPPER_ID = "shipperId"
        const val FREIGHT = "freight"
        const val SALES_TAX = "salesTax"
        const val TIMESTAMP = "timestamp"
        const val TRANSACTION_STATUS = "transactionStatus"
        const val ERR_LOC = "errLoc"
        const val ERR_MESSAGE = "errMessage"
        const val IS_FULFILLED = "fulfilled"
        const val IS_DELETED = "deleted"
        const val IS_PAID = "paid"
        const val PAYMENT_DATE = "paymentDate"
    }
}