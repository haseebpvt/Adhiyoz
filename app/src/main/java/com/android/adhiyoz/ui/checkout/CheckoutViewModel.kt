package com.android.adhiyoz.ui.checkout

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.adhiyoz.constant.PaymentMethods
import com.android.adhiyoz.data.fcm.Data
import com.android.adhiyoz.data.fcm.FcmRequest
import com.android.adhiyoz.domain.checkout.CheckoutSingleProductUseCase
import com.android.adhiyoz.domain.customer.GetCustomerDetailsFromFirestoreUseCase
import com.android.adhiyoz.domain.fcm.SendPlaceOrderMessageToManagerAppUseCase
import com.android.adhiyoz.domain.product.GetProductDetailsWithProductIdUseCase
import com.android.adhiyoz.result.Event
import com.android.adhiyoz.result.Result
import com.android.models.Customer
import com.android.models.Product
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class CheckoutViewModel @ViewModelInject constructor(
    private val getProductDetailsWithProductIdUseCase: GetProductDetailsWithProductIdUseCase,
    private val getCustomerDetailsFromFirestoreUseCase: GetCustomerDetailsFromFirestoreUseCase,
    private val checkoutSingleProductUseCase: CheckoutSingleProductUseCase,
    private val sendPlaceOrderMessageToManagerAppUseCase: SendPlaceOrderMessageToManagerAppUseCase
) : ViewModel() {

    private val _paymentMethod = MutableLiveData(PaymentMethods.COD)
    val paymentMethods: LiveData<PaymentMethods> = _paymentMethod

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


    private val _customer = MutableLiveData<Customer>()
    val customer: LiveData<Customer> = _customer

    fun loadCustomerDetails(userId: String) {
        viewModelScope.launch {
            when (val result = getCustomerDetailsFromFirestoreUseCase(userId)) {
                is Result.Success -> {
                    Log.e("custom_log", result.data.toString())
                    _customer.value = result.data
                }

                is Result.Error -> {
                    Log.e("custom_log", result.exception.toString())
                }
            }
        }
    }

    fun setPaymentMethod(paymentMethods: PaymentMethods) {
        _paymentMethod.value = paymentMethods
    }

    private val _actionPlaceOrder = MutableLiveData<Event<PaymentMethods>>()
    val actionPlaceOrder: LiveData<Event<PaymentMethods>> = _actionPlaceOrder

    fun placeOrder() {
        val paymentMethod = paymentMethods.value ?: PaymentMethods.COD
        _actionPlaceOrder.value = Event(paymentMethod)

        if (paymentMethod == PaymentMethods.COD) {
            FirebaseAuth.getInstance().currentUser?.let { codPlaceOrder(it.uid) }
        }
    }

    private fun codPlaceOrder(customerId: String) {
        viewModelScope.launch {
            when (val result = checkoutSingleProductUseCase(customerId, PaymentMethods.COD)) {
                is Result.Success -> {
                    sendNotificationToManager()
                }

                is Result.Error -> {

                }
            }
        }
    }

    private fun sendNotificationToManager() {
        val customerName = customer.value?.firstName ?: "User"
        val productName = product.value?.productName ?: "Product"

        viewModelScope.launch {
            sendPlaceOrderMessageToManagerAppUseCase(
                FcmRequest(
                    to = "/topics/manager",
                    data = Data(
                        orderId = "test_key",
                        message = "Ordered by $customerName",
                        title = "New $productName Order Received"
                    )
                )
            )
        }
    }
}