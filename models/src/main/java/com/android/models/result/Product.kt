package com.android.models.result

data class Product(
    val productId: String,

    /**
     * Stock-keeping unit
     */
    val sku: String = "",

    /**
     * Stock-keeping unit ID
     */
    val idSku: String = "",

    val vendorProductId: String,

    val productName: String,

    val productDescription: String,

    /**
     * Shop ID
     */
    val supplierId: String,

    val categoryId: String,

    val quantityPerUnit: String,

    val unitPrice: Double,

    /**
     * MSRP (Manufacturer's suggested retail price)
     */
    val msrp: Double,

    val availableSize: List<String>,

    val availableColors: List<String>,

    val size: Int,

    val color: String,

    val discount: Double,

    val unitWeight: Double,

    val unitInStock: Int,

    val unitsOnOrder: Int,

    val reorderLevel: String,

    val productAvailable: Boolean,

    val discountAvailable: Boolean,

    /**
     * Represent if this product has been ordered currently
     */
    val currentOrder: Boolean,

    val ranking: Double,

    val note: String,
)