package lotto.controller

import lotto.Validator
import lotto.model.Lotto
import lotto.view.InputView

class Controller {
    fun start() {
        val purchaseAmount = getAmount()
        val winningLotto = getWinningNum()
        val bonusNum = getBonusNum(winningLotto)

    }

    private fun getAmount(): Int {
        while (true) {
            try {
                val money = InputView.getPurchaseAmount()
                Validator.moneyValidator(money)
                return money.replace(",", "").toInt()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun generateLotto(amount: Int) {
        //로또 발행 with LottoMachine Class
    }

    private fun getWinningNum(): Lotto {
        while (true) {
            try {
                val input = InputView.getWinningNum()
                Validator.winningNumValidator(input)
                val numbers = input.split(",").map { it.trim().toInt() }
                return Lotto(numbers)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun getBonusNum(lotto: Lotto): Int {
        while (true) {
            try {
                val input = InputView.getBonusNum()
                Validator.bonusNumValidator(input)
                lotto.isDuplicate(input.toInt())
                return input.toInt()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

}