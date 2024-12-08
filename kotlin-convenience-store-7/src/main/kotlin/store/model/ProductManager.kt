package store.model

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

    private fun saveProduct(name: String, price: Int, quantity: Int, promotion: String?) {
        val existProduct = products.find { it.name == name }

        if (existProduct != null) {
            updateExistProduct(existProduct, quantity, promotion)
        } else addNewProduct(name, price, quantity, promotion)
    }

    private fun addNewProduct(name: String, price: Int, quantity: Int, promotion: String?) {
        products.add(
            Product(
                name = name,
                price = price,
                totalQuantity = quantity,
                promotionQuantity = if (promotion != null) quantity else 0,
                promotion = promotion
            )
        )
    }

    private fun updateExistProduct(existProduct: Product, quantity: Int, promotion: String?) {
        existProduct.totalQuantity += quantity

        if (promotion != null) {
            existProduct.promotionQuantity += quantity
        }
        if (existProduct.promotion == null && promotion != null) {
            existProduct.promotion = promotion
        }
    }


}