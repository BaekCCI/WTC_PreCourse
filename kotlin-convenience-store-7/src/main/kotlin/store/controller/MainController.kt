package store.controller

import store.Validator
import store.data.CartItem
import store.data.Product
import store.model.ProductManager
import store.view.InputView
import store.view.OutputView

class MainController {
    val productManager = ProductManager()
    val inputView = InputView()
    val outputView = OutputView()
    val validator = Validator()

    fun start() {
        do {
            displayProducts()
            val tempCart = handlePurchase()
            val promotionController = PromotionController(productManager, tempCart)
            val cart = promotionController.purchaseProcess()
            val extraItems = promotionController.getExtraItems()
            val noPromotionDiscountItem = promotionController.getNoPromotionDiscountedItems()
            val isMembershipDiscount = askMembershipDiscount()
            displayReceipt(cart, extraItems, noPromotionDiscountItem, isMembershipDiscount)
        } while (askAdditionalPurchase())
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

    fun handlePurchase(): Map<String, Int> {
        while (true) {
            try {
                val input = inputView.purchaseItem()
                validator.validatePurchaseInput(input)
                return addCart(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun addCart(input: String): Map<String, Int> {
        val tempCart = parseInput(input)
        productManager.checkPurchaseProduct(tempCart)

        return tempCart
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

    fun askMembershipDiscount(): Boolean {
        while (true) {
            try {
                val input = inputView.askForMembershipDiscount().lowercase().trim()
                validator.validateYesOrNoInput(input)
                return input == "y"
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun askAdditionalPurchase(): Boolean {
        while (true) {
            try {
                val input = inputView.askForAdditionalPurchase().lowercase().trim()
                validator.validateYesOrNoInput(input)
                return input == "y"
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun displayReceipt(
        cart: List<CartItem>,
        extraItems: Map<Product, Int>,
        noPromotionDiscountItem: Map<Product, Int>,
        isMembershipDiscount: Boolean
    ) {

        displayPurchaseItems(cart)
        displayExtraItem(extraItems)
        displayTotal(cart, extraItems, noPromotionDiscountItem, isMembershipDiscount)
    }

    fun displayPurchaseItems(cart: List<CartItem>) {
        outputView.displayReceiptTitle()
        cart.forEach {
            val name = it.product.name
            val purchaseAmount = it.purchaseAmount
            val totalPrice = it.product.price * purchaseAmount
            outputView.displayPurchasedItem(name, purchaseAmount, totalPrice)
        }
    }

    fun displayExtraItem(extraItems: Map<Product, Int>) {
        outputView.displayExtraItemTitle()
        extraItems.forEach { (product, amount) ->
            outputView.displayExtraItem(product.name, amount)
        }
    }

    fun displayTotal(
        cart: List<CartItem>,
        extraItems: Map<Product, Int>,
        noPromotionDiscountItem: Map<Product, Int>,
        isMembershipDiscount: Boolean
    ) {
        outputView.displayLine()
        val (totalAmount, totalPrice) = getTotalPrice(cart)
        outputView.displayReceiptTotal(totalAmount, totalPrice)
        val promotionDiscount = calculatePromotionDiscount(extraItems)
        val membershipDiscount = calculateMembershipDiscount(noPromotionDiscountItem, isMembershipDiscount)
        outputView.displayReceiptDiscount(promotionDiscount, membershipDiscount)
        val actualPay = calculateActualPay(totalPrice, promotionDiscount, membershipDiscount)
        outputView.displayActualPay(actualPay)
    }

    fun getTotalPrice(cart: List<CartItem>): Pair<Int, Int> {
        var totalPrice = 0
        var totalAmount = 0
        cart.forEach {
            totalPrice += it.product.price * it.purchaseAmount
            totalAmount += it.purchaseAmount
        }
        return Pair(totalAmount, totalPrice)
    }

    fun calculatePromotionDiscount(extraItems: Map<Product, Int>): Int {
        var promotionDiscount = 0
        extraItems.forEach { (product, amount) ->
            promotionDiscount += product.price * amount
        }
        return promotionDiscount
    }

    fun calculateMembershipDiscount(noPromotionDiscountItem: Map<Product, Int>, isMembershipDiscount: Boolean): Int {
        if (!isMembershipDiscount) return 0
        var sumPrice = 0
        noPromotionDiscountItem.forEach { (product, amount) ->
            sumPrice += product.price * amount
        }
        val membershipDiscount = (sumPrice * 0.3).toInt()
        return if (membershipDiscount > 8000) 8000 else membershipDiscount
    }

    fun calculateActualPay(totalPrice: Int, promotionDiscount: Int, membershipDiscount: Int): Int {
        return totalPrice - promotionDiscount - membershipDiscount
    }


}