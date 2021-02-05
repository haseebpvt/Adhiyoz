package com.android.adhiyoz.data.product

import com.android.models.Product
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface ProductDataSource {
    fun getAllProducts(): List<Product>

    fun getProductDetails(productId: String): Product
}

class FirestoreProductDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProductDataSource {

    override fun getAllProducts(): List<Product> {
        val task = firestore
            .collection(PRODUCT)
            .get()
        val snapshot = Tasks.await(task, 20, TimeUnit.SECONDS)
        return snapshot.documents.map { parseProductItem(it) }
    }

    override fun getProductDetails(productId: String): Product {
        val task = firestore
            .collection(PRODUCT)
            .document(productId)
            .get()
        val snapshot = Tasks.await(task, 20, TimeUnit.SECONDS)
        return parseProductItem(snapshot)
    }

    private fun parseProductItem(snapshot: DocumentSnapshot): Product {
        return Product(
            productId = snapshot.id,
            sku = snapshot[SKU] as? String,
            idSku = snapshot[ID_SKU] as? String,
            vendorProductId = snapshot[VENDOR_PRODUCT_ID] as? String,
            productName = snapshot[PRODUCT_NAME] as? String,
            productDescription = snapshot[PRODUCT_DESCRIPTION] as? String,
            supplierId = snapshot[SUPPLIER_ID] as? String,
            categoryId = snapshot[CATEGORY_ID] as? String,
            quantityPerUnit = snapshot[QUANTITY_PER_UNIT] as? Int,
            unitPrice = snapshot[UNIT_PRICE] as? Double,
            msrp = snapshot.getLong(MSRP)?.toDouble(),
            availableSize = snapshot[AVAILABLE_SIZE] as? List<String>,
            availableColors = snapshot[AVAILABLE_COLOR] as? List<String>,
            size = snapshot[SIZE] as? Double,
            color = snapshot[COLOR] as? String,
            discount = snapshot[DISCOUNT] as? Double? ?: 0.0,
            unitWeight = snapshot[UNIT_WEIGHT] as? Double,
            unitInStock = snapshot[UNIT_IN_STOCK] as? Int,
            unitsOnOrder = snapshot[UNIT_IN_STOCK] as? Int,
            reorderLevel = snapshot[REORDER_LEVEL] as? String,
            productAvailable = snapshot[PRODUCT_AVAILABLE] as? Boolean,
            discountAvailable = snapshot[DISCOUNT_AVAILABLE] as? Boolean,
            currentOrder = snapshot[CURRENT_ORDER] as? Boolean,
            ranking = snapshot[RANKING] as? Double,
            picture = snapshot[PICTURE] as? String,
            note = snapshot[NOTE] as? String
        )
    }

    companion object {
        /**
         * Firestore constants for product
         */
        const val PRODUCT = "product"
        const val PRODUCT_ID = "productId"
        const val SKU = "sku"
        const val ID_SKU = "idSku"
        const val VENDOR_PRODUCT_ID = "vendorProductId"
        const val PRODUCT_NAME = "productName"
        const val PRODUCT_DESCRIPTION = "productDescription"
        const val SUPPLIER_ID = "supplierId"
        const val CATEGORY_ID = "categoryId"
        const val QUANTITY_PER_UNIT = "quantityPerUnit"
        const val UNIT_PRICE = "unitPrice"
        const val MSRP = "msrp"
        const val AVAILABLE_SIZE = "availableSize"
        const val AVAILABLE_COLOR = "availableColor"
        const val SIZE = "size"
        const val COLOR = "color"
        const val DISCOUNT = "discount"
        const val UNIT_WEIGHT = "unitWeight"
        const val UNIT_IN_STOCK = "unitInStock"
        const val UNITS_ON_ORDER = "unitsOnOrder"
        const val REORDER_LEVEL = "reorderLevel"
        const val PRODUCT_AVAILABLE = "productAvailable"
        const val DISCOUNT_AVAILABLE = "discountAvailable"
        const val CURRENT_ORDER = "currentOrder"
        const val RANKING = "ranking"
        const val PICTURE = "picture"
        const val NOTE = "note"
    }
}