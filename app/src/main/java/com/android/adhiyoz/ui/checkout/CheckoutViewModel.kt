package com.android.adhiyoz.ui.checkout

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.adhiyoz.domain.product.GetProductDetailsWithProductIdUseCase
import com.android.adhiyoz.result.Result
import com.android.models.Product
import kotlinx.coroutines.launch

class CheckoutViewModel @ViewModelInject constructor(
    private val getProductDetailsWithProductIdUseCase: GetProductDetailsWithProductIdUseCase
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun loadProductDetails(productId: String) {
        viewModelScope.launch {
            when (val result = getProductDetailsWithProductIdUseCase(productId)) {
                is Result.Success -> {
                    _product.value = result.data
                    Log.d("custom_log", "Success: ${result.data}")
                }

                is Result.Error -> {
                    Log.d("custom_log", "Failed: ${result.exception}")
                }
            }
        }
    }
}