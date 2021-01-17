package com.android.adhiyoz.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.adhiyoz.domain.category.GetCategoryListUseCase
import com.android.adhiyoz.domain.product.GetAllProductsUseCase
import com.android.adhiyoz.result.Result
import com.android.models.Category
import com.android.models.Product
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _categoryItems = MutableLiveData<List<Category>>().apply {
        viewModelScope.launch {
            when (val result = getCategoryListUseCase()) {
                is Result.Success -> {
                    value = result.data
                }

                is Result.Error -> {

                }
            }
        }
    }
    val categoryItems: LiveData<List<Category>> = _categoryItems

    private val _productItems = MutableLiveData<List<Product>>().apply {
        viewModelScope.launch {
            when (val result = getAllProductsUseCase()) {
                is Result.Success -> {
                    value = result.data.also { Log.d("custom_log", it.toString()) }
                }

                is Result.Error -> {
                    Log.d("custom_log", result.exception.toString())
                }
            }
        }
    }
    val productItems: LiveData<List<Product>> = _productItems
}