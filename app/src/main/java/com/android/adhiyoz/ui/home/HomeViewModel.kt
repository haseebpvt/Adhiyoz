package com.android.adhiyoz.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.models.Category
import com.android.shared.domain.category.GetCategoryListUseCase
import com.android.shared.result.Result
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _categoryItems = MutableLiveData<List<Category>>().apply {
        viewModelScope.launch {
            when (val result = getCategoryListUseCase()) {
                is Result.Success -> {
                    Log.d("data_log", result.data.toString())
                }

                is Result.Error -> {

                }
            }
        }
    }
    val categoryItems: LiveData<List<Category>> = _categoryItems
}