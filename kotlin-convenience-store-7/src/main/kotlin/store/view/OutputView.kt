package store.view

import store.data.Product

const val THOUSAND_SEPARATOR_REGEX = "(?<=\\d)(?=(\\d{3})+(?!\\d))"

enum class OutputMessage(private val message: String) {
    WELCOME("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다."),
    PRODUCT_INFO("- %s %s원 %s %s"),
    RECEIPT_TITLE("===========W 편의점=============\n상품명\t\t수량\t금액"),
    RECEIPT_PURCHASE_ITEM("%s\t%d\t%s"),
    RECEIPT_EXTRA_ITEM_TITLE("===========증\t정============="),
    RECEIPT_EXTRA_ITEM_INFO("%s\t%d"),
    RECEIPT_LINE("=============================="),
    RECEIPT_TOTAL_PRICE("총구매액\t%d\t%s"),
    RECEIPT_PROMOTION_DISCOUNT("행사할인\t\t-%s"),
    RECEIPT_MEMBERSHIP_DISCOUNT("멤버십할인\t\t-%s"),
    RECEIPT_ACTUAL_PAY("내실돈\t\t%s");

    fun format(): String {
        return message
    }

    fun format(name: String, price: Int, quantity: Int, promotion: String?): String {
        val formattedPrice = price.toString().replace(THOUSAND_SEPARATOR_REGEX.toRegex(), ",")
        val displayQuantity = if (quantity == 0) "재고 없음" else "${quantity}개"
        val promotionText = promotion ?: ""

        return message.format(name, formattedPrice, displayQuantity, promotionText)
    }

    fun format(name: String, purchaseAmount: Int, price: Int): String {
        val formattedPrice = price.toString().replace(THOUSAND_SEPARATOR_REGEX.toRegex(), ",")
        return message.format(name, purchaseAmount, formattedPrice)
    }

    fun format(name: String, extraAmount: Int): String {
        return message.format(name, extraAmount)
    }

    fun format(purchaseAmount: Int, totalPrice: Int): String {
        val formattedPrice = totalPrice.toString().replace(THOUSAND_SEPARATOR_REGEX.toRegex(), ",")
        return message.format(purchaseAmount, formattedPrice)
    }

    fun format(price: Int): String {
        val formattedPrice = price.toString().replace(THOUSAND_SEPARATOR_REGEX.toRegex(), ",")
        return message.format(formattedPrice)
    }
}

class OutputView {

    fun displayWelcomeMessage() {
        println(OutputMessage.WELCOME.format())
    }

    fun displayProductInfo(name: String, price: Int, quantity: Int, promotion: String?) {
        println(OutputMessage.PRODUCT_INFO.format(name, price, quantity, promotion))
    }

    fun display(products: List<Product>) {
        products.forEach { product ->
            println(OutputMessage.PRODUCT_INFO.format(product.name, product.price, product.quantity, product.promotion))
            if (product.promotion != null && !products.any { it.name == product.name && it.promotion == null }) {
                println(OutputMessage.PRODUCT_INFO.format(product.name, product.price, 0, null))
            }
        }
    }

    fun displayReceiptTitle() {
        println(OutputMessage.RECEIPT_TITLE.format())
    }

    fun displayPurchasedItem(name: String, amount: Int, price: Int) {
        println(OutputMessage.RECEIPT_PURCHASE_ITEM.format(name, amount, price))
    }

    fun displayExtraItemTitle() {
        println(OutputMessage.RECEIPT_EXTRA_ITEM_TITLE.format())
    }

    fun displayExtraItem(name: String, amount: Int) {
        println(OutputMessage.RECEIPT_EXTRA_ITEM_INFO.format(name, amount))
    }

    fun displayLine() {
        println(OutputMessage.RECEIPT_LINE.format())
    }

    fun displayReceiptTotal(totalAmount: Int, totalPrice: Int) {
        println(OutputMessage.RECEIPT_LINE.format())
        println(OutputMessage.RECEIPT_TOTAL_PRICE.format(totalAmount, totalPrice))
    }

    fun displayReceiptDiscount(promotionDiscount: Int, membershipDiscount: Int) {
        println(OutputMessage.RECEIPT_PROMOTION_DISCOUNT.format(promotionDiscount))
        println(OutputMessage.RECEIPT_MEMBERSHIP_DISCOUNT.format(membershipDiscount))
    }

    fun displayActualPay(actualPay: Int) {
        println(OutputMessage.RECEIPT_ACTUAL_PAY.format(actualPay))
    }
}