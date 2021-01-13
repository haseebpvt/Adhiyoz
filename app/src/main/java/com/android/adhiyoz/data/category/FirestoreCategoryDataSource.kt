package com.android.adhiyoz.data.category

import com.android.models.Category
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface CategoryDataSource {
    fun getCategories(): List<Category>
}

internal class FirestoreCategoryDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : CategoryDataSource {

    override fun getCategories(): List<Category> {
        val task = firestore
            .collection(CATEGORY)
            .whereEqualTo(ACTIVE, true)
            .get()
        val snapshot = Tasks.await(task, 20, TimeUnit.SECONDS)
        return snapshot.documents.map { parseCategoryItem(it) }
    }

    private fun parseCategoryItem(snapshot: DocumentSnapshot): Category {
        return Category(
            categoryId = snapshot[CATEGORY_ID] as? String ?: "",
            categoryName = snapshot[CATEGORY_NAME] as? String ?: "",
            description = snapshot[DESCRIPTION] as? String ?: "",
            image = snapshot[IMAGE_URL] as? String ?: "",
            active = snapshot[ACTIVE] as? Boolean ?: false
        )
    }

    companion object {
        /**
         * Firestore constants.
         */
        private const val CATEGORY = "category"
        private const val CATEGORY_ID = "categoryId"
        private const val CATEGORY_NAME = "categoryName"
        private const val DESCRIPTION = "description"
        private const val ACTIVE = "active"
        private const val IMAGE_URL = "imageUrl"
    }
}