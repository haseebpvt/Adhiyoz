package com.android.adhiyoz.domain.category

import com.android.adhiyoz.data.category.CategoryRepository
import com.android.adhiyoz.di.IoDispatcher
import com.android.adhiyoz.result.Result
import com.android.models.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Result<List<Category>> = withContext(dispatcher) {
        val result = try {
            categoryRepository.getCategories()
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}