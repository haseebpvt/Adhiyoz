package com.android.adhiyoz.data.customer

import com.android.models.Customer
import javax.inject.Inject

interface CustomerRepository {
    fun saveCustomerDetails(userId: String, customer: Customer)

    fun getCustomerDetails(userId: String): Customer
}

class FirestoreCustomerRepository @Inject constructor(
    private val customerDataSource: CustomerDataSource
) : CustomerRepository {
    override fun saveCustomerDetails(userId: String, customer: Customer) {
        customerDataSource.saveCustomerDetails(userId, customer)
    }

    override fun getCustomerDetails(userId: String): Customer {
        return customerDataSource.getCustomerDetails(userId)
    }
}