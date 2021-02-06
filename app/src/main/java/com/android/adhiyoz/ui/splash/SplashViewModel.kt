package com.android.adhiyoz.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.adhiyoz.domain.customer.AddNewCustomerDataToFirestoreUseCase
import com.android.adhiyoz.result.Result
import com.android.models.Customer
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
    private val addNewCustomerDataToFirestoreUseCase: AddNewCustomerDataToFirestoreUseCase
) : ViewModel() {

    fun saveUserDetails(userId: String, customer: Customer) {
        viewModelScope.launch {
            when (val result = addNewCustomerDataToFirestoreUseCase(userId, customer)) {
                is Result.Success -> {

                }

                is Result.Error -> {

                }
            }
        }
    }
}