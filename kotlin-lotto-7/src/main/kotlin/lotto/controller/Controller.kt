package lotto.controller

import lotto.Validator
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.view.InputView
import lotto.view.OutputView

class Controller {
    fun start() {
        val purchaseAmount = getAmount()
        generateLotto(purchaseAmount)
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

    fun generateLotto(purchaseAmount: Int) {
        val lottoMachine = LottoMachine(purchaseAmount)
        val purchasedLotto = lottoMachine.getPurchasedLotto()
        OutputView.displayPurchasedLotto(purchasedLotto)

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