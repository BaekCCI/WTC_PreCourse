package store.model

import camp.nextstep.edu.missionutils.DateTimes
import store.data.Product
import store.data.Promotion
import java.time.LocalDate

const val PROMOTION_DATA_PATH = "src/main/resources/promotions.md"

class PromotionManager {
    private val promotions: MutableList<Promotion> = mutableListOf()

    init {
        loadPromotion()
    }

    private fun loadPromotion() {
        val loadedPromotions = java.io.File(PROMOTION_DATA_PATH).readLines().drop(1)
        loadedPromotions.forEach { promotion ->
            val info = promotion.split(",").map { it.trim() }
            savePromotion(info[0], info[1].toInt(), info[2].toInt(), info[3], info[4])
        }
    }

    private fun savePromotion(name: String, buy: Int, get: Int, start_date: String, end_date: String) {
        promotions.add(
            Promotion(
                name = name,
                buy = buy,
                get = get,
                start_date = start_date,
                end_date = end_date
            )
        )
    }

    fun isInPromotionDate(name: String): Boolean {
        val promotion = promotions.find { it.name == name }
        val today = DateTimes.now().toLocalDate()
        if (promotion != null) {
            val start_date = getLocalDate(promotion.start_date)
            val end_date = getLocalDate(promotion.end_date)
            return (today.isAfter(start_date) && today.isBefore(end_date))
        }
        return false
    }

    private fun getLocalDate(date: String): LocalDate {
        val (year, month, day) = date.split("-").map { it.toInt() }
        return LocalDate.of(year, month, day)
    }

    fun getBuy(product: Product): Int {
        return promotions.find { it.name == product.promotion }?.buy ?: 0
    }

    fun getGet(product: Product): Int {
        return promotions.find { it.name == product.promotion }?.get ?: 0
    }
}