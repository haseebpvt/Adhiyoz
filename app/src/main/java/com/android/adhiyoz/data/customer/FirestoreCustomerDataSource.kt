package com.android.adhiyoz.data.customer

import com.android.models.Customer
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface CustomerDataSource {
    fun saveCustomerDetails(userId: String, customer: Customer)

    fun getCustomerDetails(userId: String): Customer
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

    override fun getCustomerDetails(userId: String): Customer {
        val task = firestore
            .collection(CUSTOMER)
            .document(userId)
            .get()

        val snapshot = Tasks.await(task, 20, TimeUnit.SECONDS)
        return parseCustomerItem(snapshot)
    }

    private fun parseCustomerItem(snapshot: DocumentSnapshot): Customer {
        return Customer(
            customerId = snapshot[CUSTOMER_ID] as String? ?: "",
            firstName = snapshot[FIRST_NAME] as String? ?: "N/A",
            phone = snapshot[PHONE_NUMBER] as String? ?: "N/A",
            email = snapshot[EMAIL] as String? ?: "N/A",
            photo = snapshot[PHOTO_URL] as String? ?: "N/A",
        )
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