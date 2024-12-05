package lotto.view

import camp.nextstep.edu.missionutils.Console

object InputView {
    fun getPurchaseAmount(): String {
        println("구매금액을 입력해 주세요.")
        return Console.readLine()
    }

    fun getWinningNum(): String {
        println("당첨 번호를 입력해 주세요.")
        return Console.readLine()
    }

    fun getBonusNum(): String {
        println("보너스 번호를 입력해 주세요.")
        return Console.readLine()
    }
}