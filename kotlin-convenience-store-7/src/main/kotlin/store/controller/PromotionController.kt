package store.controller

import store.Validator
import store.data.CartItem
import store.data.Product
import store.data.Promotion
import store.model.ProductManager
import store.model.PromotionManager

import store.view.InputView

class PromotionController(val productManager: ProductManager, val tempCart: Map<String, Int>) {
    val inputView = InputView()
    val validator = Validator()
    val promotionManager = PromotionManager()

    private val cart: MutableList<CartItem> = mutableListOf()

    fun purchaseProcess(): List<CartItem> {
        val promotionItems = getPromotionItems()
        processGeneralItems()
        handlePromotionItem(promotionItems)
        return cart
    }

    fun getPromotionItems(): MutableMap<Product, Int> {
        val promotionItems: MutableMap<Product, Int> = mutableMapOf()
        tempCart.forEach { (name, purchaseAmount) ->
            if (productManager.checkIsPromotion(name)) {
                promotionItems[productManager.getPromotionProduct(name)] = purchaseAmount
            }
        }
        return promotionItems
    }

    fun processGeneralItems() {
        tempCart.forEach { (name, purchaseAmount) ->
            if (!productManager.checkIsPromotion(name)) {
                val generalItem = productManager.getGeneralProduct(name)
                addItem(generalItem, purchaseAmount)
            }
        }
    }

    fun addItem(product: Product, purchaseAmount: Int) {
        cart.add(
            CartItem(
                product = product,
                purchaseAmount = purchaseAmount
            )
        )
    }

    fun handlePromotionItem(promotionItems: Map<Product, Int>) {
        if (promotionItems.isNotEmpty()) {
            checkQuantity(promotionItems)
        }
    }

    fun checkQuantity(promotionItems: Map<Product, Int>) {
        promotionItems.forEach { (product, purchaseAmount) ->
            if (purchaseAmount <= product.quantity) {
                isNeedAddExtraItem(product, purchaseAmount)
            } else {
                confirmNoDisCount(product, purchaseAmount)
            }
        }
    }

    fun isNeedAddExtraItem(product: Product, purchaseAmount: Int) {
        val buy = promotionManager.getBuy(product)
        val get = promotionManager.getGet(product)
        val remainAmount = purchaseAmount % (buy + get)
        if (remainAmount == 0) {
            addItem(product, purchaseAmount)
        } else if (remainAmount == buy && purchaseAmount + get <= product.quantity) {
            addExtraItemOrNot(product, purchaseAmount, buy, get)
        } else {
            addItem(product, purchaseAmount - remainAmount)
            confirmNoDisCount(product, remainAmount)
        }
    }

    fun addExtraItemOrNot(product: Product, purchaseAmount: Int, buy: Int, get: Int) {
        if (askAddExtraItem(product.name)) {
            addItem(product, purchaseAmount + get)
        } else {
            addItem(product, purchaseAmount)
        }
    }

    fun askAddExtraItem(name: String): Boolean {
        while (true) {
            try {
                val input = inputView.askAddPromotionItem(name).lowercase().trim()
                validator.validateYesOrNoInput(input)
                return input == "y"
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun confirmNoDisCount(product: Product, purchaseAmount: Int) {
        addItem(product, product.quantity)
        val noDisCountAmount = purchaseAmount - product.quantity
        if (askConfirmNoDisCount(product.name, noDisCountAmount)) {
            val generalProduct = productManager.getGeneralProduct(product.name)
            addItem(generalProduct, noDisCountAmount)
        }
    }

    fun askConfirmNoDisCount(name: String, noDisCountAmount: Int): Boolean {
        while (true) {
            try {
                val input = inputView.askForNonDiscountedItems(name, noDisCountAmount).lowercase().trim()
                validator.validateYesOrNoInput(input)
                return input == "y"
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun getExtraItems(): Map<Product, Int> {
        val extraItems: MutableMap<Product, Int> = mutableMapOf()
        cart.forEach {
            if (it.product.promotion != null) {
                extraItems[it.product] = getExtraItemAmount(it.product, it.purchaseAmount)
            }
        }
        return extraItems
    }

    fun getExtraItemAmount(product: Product, purchaseAmount: Int): Int {
        val buy = promotionManager.getBuy(product)
        val get = promotionManager.getGet(product)
        return purchaseAmount / (buy + get)
    }

    fun getNoPromotionDiscountedItems(): Map<Product, Int> {
        val noPromotionDiscountedItems: MutableMap<Product, Int> = mutableMapOf()
        cart.forEach {
            val amount = calculateNoDiscountItemAmount(it)
            noPromotionDiscountedItems[it.product] = amount
        }
        return noPromotionDiscountedItems
    }

    fun calculateNoDiscountItemAmount(cartItem: CartItem): Int {
        var amount = cartItem.purchaseAmount
        if (cartItem.product.promotion != null) {
            val buy = promotionManager.getBuy(cartItem.product)
            val get = promotionManager.getGet(cartItem.product)
            amount = amount % (buy + get)
        }
        return amount
    }

}