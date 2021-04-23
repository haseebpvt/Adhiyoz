package com.android.adhiyoz.ui.payment.gpay

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.adhiyoz.constant.PaymentMethods
import com.android.adhiyoz.domain.checkout.CheckoutSingleProductUseCase
import com.android.adhiyoz.result.Result
import kotlinx.coroutines.launch

class GooglePayViewModel @ViewModelInject constructor(
    private val checkoutSingleProductUseCase: CheckoutSingleProductUseCase
) : ViewModel() {

    fun placeOrder(customerId: String) {
        viewModelScope.launch {
            when (val result =
                checkoutSingleProductUseCase(customerId, PaymentMethods.GOOGLE_PAY)) {
                is Result.Success -> {

                }

                is Result.Error -> {

                }
            }
        }
    }
}