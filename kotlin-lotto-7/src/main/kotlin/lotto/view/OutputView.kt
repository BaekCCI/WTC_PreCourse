package lotto.view

import lotto.model.Lotto
import lotto.model.LottoPrize
 // ?<=\\d : Lookbehind, 앞에 숫자가 있는 위치
 // ?=\\d{3} : Lookahead, 뒤에 숫자 3개가 있는 위치
 // ?!\\d : nagative Lookahead, 뒤에 숫자가 더이상 없어야 함.
const val REGEX = "(?<=\\d)(?=(\\d{3})+(?!\\d))"

object OutputView {
    fun displayPurchasedLotto(lotto: List<Lotto>) {
        println()
        println("${lotto.size}개를 구매했습니다.")
        lotto.forEach {
            println("${it.getLotto()}")
        }
    }

    fun displayLottoResultTitle() {
        println()
        println("당첨 통계\n---")
    }

    fun displayLottoResult(rank: LottoPrize, count: Int) {
        if(rank == LottoPrize.SECOND){
            println("${rank.matchCount}개 일치, 보너스 볼 일치 (${rank.prize.toString().replace(REGEX.toRegex(),",")}원) - ${count}개")
        }
        else{
            println("${rank.matchCount}개 일치 (${rank.prize.toString().replace(REGEX.toRegex(),",")}원) - ${count}개")
        }
    }

    fun displayPrizeRatio(prizeRatio: Double) {
        println("총 수익률은 ${"%.1f".format(prizeRatio)}%입니다.")
    }

}