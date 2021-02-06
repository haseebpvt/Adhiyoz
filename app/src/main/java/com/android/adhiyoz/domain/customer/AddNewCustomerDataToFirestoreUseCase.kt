package com.android.adhiyoz.domain.customer

import com.android.adhiyoz.data.customer.CustomerRepository
import com.android.adhiyoz.di.IoDispatcher
import com.android.adhiyoz.result.Result
import com.android.models.Customer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddNewCustomerDataToFirestoreUseCase @Inject constructor(
    private val customerRepository: CustomerRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userId: String, customer: Customer): Result<Unit> =
        withContext(dispatcher) {
            val result = try {
                customerRepository.saveCustomerDetails(userId, customer)
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }

            return@withContext Result.Success(result)
        }
}