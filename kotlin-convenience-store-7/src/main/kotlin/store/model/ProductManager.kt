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

    fun getTotalQuantity(name: String): Int {
        var totalQuantity = 0
        products.forEach {
            if (it.name == name) totalQuantity += it.quantity
        }
        return totalQuantity
    }

    fun checkIsPromotion(name: String): Boolean {
        val promotionManager = PromotionManager()
        val product = products.find { it.name == name && it.promotion != null }
        return product != null && promotionManager.isInPromotionDate(product.promotion!!)
    }

    fun getGeneralProduct(name: String): Product {
        return products.find { it.name == name && it.promotion == null }!!
    }

    fun getPromotionProduct(name: String): Product {
        return products.find { it.name == name && it.promotion != null }!!
    }

    fun isPromotionQuantitySufficient(name: String, amount: Int): Boolean {
        val product = products.find { it.name == name && it.promotion != null }
        return amount < product!!.quantity
    }

    fun getPromotion(name: String): String {
        return products.find { it.name == name && it.promotion != null }!!.promotion!!
    }


}