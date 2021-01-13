package com.android.adhiyoz.data.category

import com.android.models.Category
import javax.inject.Inject

interface CategoryRepository {
    fun getCategories(): List<Category>
}

internal class FirestoreCategoryRepository @Inject constructor(
    private val firestoreCategoryDataSource: CategoryDataSource
) : CategoryRepository {

    override fun getCategories(): List<Category> {
        return firestoreCategoryDataSource.getCategories()
    }
}