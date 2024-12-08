package store.data

data class Product(
    val name: String,
    val price: Int,
    var totalQuantity: Int,
    var promotionQuantity: Int,
    var promotion: String?
)
