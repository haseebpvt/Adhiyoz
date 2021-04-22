package com.android.adhiyoz.domain.customer

import com.android.adhiyoz.data.customer.CustomerRepository
import com.android.adhiyoz.di.IoDispatcher
import com.android.adhiyoz.result.Result
import com.android.models.Customer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetCustomerDetailsFromFirestoreUseCase @Inject constructor(
    private val customerRepository: CustomerRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userId: String): Result<Customer> = withContext(dispatcher) {
        val result = try {
            customerRepository.getCustomerDetails(userId)
        } catch (e: Exception) {
            Timber.e("Customer details: $e")
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}