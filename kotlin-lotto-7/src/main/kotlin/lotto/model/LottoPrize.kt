package lotto.model

import lotto.constant.ErrorMsg

enum class LottoPrize(val prize: Int, val matchCount: Int, val bonusMatch: Boolean) {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    NOTHING(0, 0, false);

    companion object {
        fun getRank(matchCount: Int, bonusMatch: Boolean): LottoPrize {
            if (matchCount in 0..2) return NOTHING
            else if (matchCount == 5 && bonusMatch) return SECOND
            else if (matchCount == 5) return THIRD
            return entries.find { it.matchCount == matchCount }
                ?: throw IllegalArgumentException(ErrorMsg.INVALID_MATCHING.format())
        }
    }
}