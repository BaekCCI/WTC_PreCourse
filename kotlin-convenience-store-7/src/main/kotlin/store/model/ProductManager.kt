package store.model

import store.ErrorMessage
import store.data.Product

const val INVENTORY_PATH = "src/main/resources/products.md"

class ProductManager {
    private val products: MutableList<Product> = mutableListOf()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        val inventory = java.io.File(INVENTORY_PATH).readLines().drop(1) // 첫 줄 제외
        inventory.forEach { item ->
            val info = item.split(",").map { it.trim() }
            val promotion = if (info[3] == "null") null else info[3]
            saveProduct(info[0], info[1].toInt(), info[2].toInt(), promotion)
        }
    }

    fun saveProduct(name: String, price: Int, quantity: Int, promotion: String?) {
        products.add(
            Product(
                name = name,
                price = price,
                quantity = quantity,
                promotion = promotion
            )
        )
    }


    fun get(): List<Product> {
        return products
    }

    fun checkPurchaseProduct(tempCart: Map<String, Int>) {
        tempCart.forEach { (name, purchaseAmount) ->
            val matchProduct = products.filter { it.name == name }
            var totalQuantity = 0
            matchProduct.forEach {
                totalQuantity += it.quantity
            }
            require(matchProduct.isNotEmpty()) { ErrorMessage.PRODUCT_NOT_FOUND.format() }
            require(purchaseAmount <= totalQuantity) { ErrorMessage.EXCEED_STOCK_QUANTITY.format() }
        }
    }


}