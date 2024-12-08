package lotto.view

import lotto.constant.*
import lotto.model.Lotto
import lotto.model.LottoPrize


object OutputView {
    fun displayPurchasedLotto(lotto: List<Lotto>) {
        println()
        println(OutputMsg.PURCHASED_LOTTO.format(lotto.size))
        lotto.forEach {
            println("${it.getLotto()}")
        }
    }

    fun displayLottoResultTitle() {
        println()
        println(OutputMsg.WINNING_STATISTICS_TITLE.format())
    }

    fun displayLottoResult(rank: LottoPrize, count: Int) {
        val prize = rank.prize.toString().replace(THOUSAND_SEPARATOR_REGEX.toRegex(), COMMA)
        if (rank == LottoPrize.SECOND) {
            println(OutputMsg.WINNING_STATISTICS_BONUS.format(rank.matchCount, prize, count))
        } else {
            println(OutputMsg.WINNING_STATISTICS.format(rank.matchCount, prize, count))
        }
    }

    fun displayPrizeRatio(prizeRatio: Double) {
        println(OutputMsg.TOTAL_PRIZE_RATIO.format(prizeRatio))
    }

}