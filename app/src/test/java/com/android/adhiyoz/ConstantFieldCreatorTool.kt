package com.android.adhiyoz

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ConstantFieldCreatorTool {
    @Test
    fun addition_isCorrect() {
        var text = "val orderId: String,\n" +
                "\n" +
                "    val customerId: String,\n" +
                "\n" +
                "    val orderNumber: String,\n" +
                "\n" +
                "    val paymentId: String,\n" +
                "\n" +
                "    val orderDate: Long,\n" +
                "\n" +
                "    val shipDate: Long,\n" +
                "\n" +
                "    val requiredDate: Long,\n" +
                "\n" +
                "    val shipperId: String,\n" +
                "\n" +
                "    val freight: String,\n" +
                "\n" +
                "    val salesTax: Double,\n" +
                "\n" +
                "    val timestamp: Long,\n" +
                "\n" +
                "    val transactionStatus: String,\n" +
                "\n" +
                "    val errLoc: String,\n" +
                "\n" +
                "    val errMessage: String,\n" +
                "\n" +
                "    val fulfilled: Boolean,\n" +
                "\n" +
                "    val deleted: Boolean,\n" +
                "\n" +
                "    val paid: Boolean,\n" +
                "\n" +
                "    val paymentDate: Long,"

        val list = text
            .replace(":", "")
            .replace("String", "")
            .replace("Long", "")
            .replace("Double", "")
            .replace("val", "")
            .replace("var", "")
            .replace(" ", "")
            .replace("\n", "")
            .trim()
            .split(",")

        val pascalList = list.map {
            val pascal = it.camelToSnakeCase().toUpperCase()
            "const val $pascal = \"$it\"\n"
        }

        print(pascalList.joinToString().replace(",", "").trim())
    }

    val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
    val snakeRegex = "_[a-zA-Z]".toRegex()

    // String extensions
    fun String.camelToSnakeCase(): String {
        return camelRegex.replace(this) {
            "_${it.value}"
        }.toLowerCase()
    }

    fun String.snakeToLowerCamelCase(): String {
        return snakeRegex.replace(this) {
            it.value.replace("_", "")
                .toUpperCase()
        }
    }

    fun String.snakeToUpperCamelCase(): String {
        return this.snakeToLowerCamelCase().capitalize()
    }
}