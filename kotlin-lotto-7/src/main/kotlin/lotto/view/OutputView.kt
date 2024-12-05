package lotto.view

import lotto.model.Lotto

object OutputView {
    fun displayPurchasedLotto(lotto : List<Lotto>){
        println()
        println("${lotto.size}개를 구매했습니다.")
        lotto.forEach {
            println("${it.getLotto()}")
        }
    }
}