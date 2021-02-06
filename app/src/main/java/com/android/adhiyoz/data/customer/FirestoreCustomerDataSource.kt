package com.android.adhiyoz.data.customer

import com.android.models.Customer
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface CustomerDataSource {
    fun saveCustomerDetails(userId: String, customer: Customer)
}

class FirestoreCustomerDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : CustomerDataSource {

    override fun saveCustomerDetails(userId: String, customer: Customer) {
        val task = firestore
            .collection(CUSTOMER)
            .document(userId)
            .set(
                mapOf(
                    CUSTOMER_ID to userId,
                    FIRST_NAME to customer.firstName,
                    PHONE_NUMBER to customer.phone,
                    EMAIL to customer.email,
                    PHOTO_URL to customer.photo
                )
            )

        Tasks.await(task, 20, TimeUnit.SECONDS)
    }

    companion object {
        const val CUSTOMER = "customer"
        const val FIRST_NAME = "firstName"
        const val PHONE_NUMBER = "phoneNumber"
        const val EMAIL = "email"
        const val CUSTOMER_ID = "customerId"
        const val PHOTO_URL = "photoUrl"
    }
}