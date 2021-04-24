package com.android.adhiyoz.domain.fcm

import com.android.adhiyoz.data.fcm.FcmRepository
import com.android.adhiyoz.data.fcm.FcmRequest
import com.android.adhiyoz.di.IoDispatcher
import com.android.adhiyoz.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendPlaceOrderMessageToManagerAppUseCase @Inject constructor(
    private val fcmRepository: FcmRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(fcmRequest: FcmRequest): Result<Unit> = withContext(dispatcher) {
        val result = try {
            fcmRepository.sendCloudMessage(fcmRequest)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}