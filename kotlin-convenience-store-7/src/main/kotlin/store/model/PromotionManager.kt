package store.model

import store.data.Promotion

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
}