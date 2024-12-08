package store.controller

import store.Validator
import store.model.ProductManager
import store.model.PromotionManager
import store.view.InputView
import store.view.OutputMessage
import store.view.OutputView

class Controller {
    val productManager = ProductManager()
    val promotionManager = PromotionManager()
    val inputView = InputView()
    val outputView = OutputView()
    val validator = Validator()

    fun start() {
        displayProducts()


    }

    fun displayProducts() {
        outputView.displayWelcomeMessage()
        val products = productManager.get()
        products.forEach { product ->
            outputView.displayProductInfo(product.name, product.price, product.quantity, product.promotion)
            if (product.promotion != null && !products.any { it.name == product.name && it.promotion == null }) {
                outputView.displayProductInfo(product.name, product.price, 0, null)
            }
        }
    }

    fun handlePurchase() {
        while (true) {
            try {
                val input = inputView.purchaseItem()
                validator.validatePurchaseInput(input)
                addCart(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun addCart(input: String) {
        val tempCart = parseInput(input)
        val
        productManager.checkPurchaseProduct(tempCart)
        tempCart.forEach{(name,amount)->


        }
    }

    fun parseInput(input: String): Map<String, Int> {
        val cleanedItems = input.replace("[\\[\\]\\s]".toRegex(), "").split(",")
        val tempCart: MutableMap<String, Int> = mutableMapOf()
        cleanedItems.forEach {
            val item = it.split("-")
            tempCart[item[0]] = tempCart.getOrDefault(item[0], 0) + item[1].toInt()
        }
        return tempCart
    }
}