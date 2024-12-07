package lotto.controller

import lotto.Validator
import lotto.model.Lotto
import lotto.model.LottoCalculator
import lotto.model.LottoMachine
import lotto.model.LottoPrize
import lotto.view.InputView
import lotto.view.OutputView

class Controller {
    fun start() {
        val purchasePrice = getAmount()
        val purchaseLotto = generateLotto(purchasePrice)
        val winningLotto = getWinningNum()
        val bonusNum = getBonusNum(winningLotto)
        getResult(purchasePrice, purchaseLotto, winningLotto, bonusNum)
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

    fun generateLotto(purchasePrice: Int): List<Lotto> {
        val lottoMachine = LottoMachine(purchasePrice)
        val purchasedLotto = lottoMachine.getPurchasedLotto()
        OutputView.displayPurchasedLotto(purchasedLotto)
        return purchasedLotto
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

    private fun getResult(purchasePrice: Int, purchaseLotto: List<Lotto>, winningLotto: Lotto, bonusNum: Int) {
        val calculator = LottoCalculator(purchasePrice, purchaseLotto, winningLotto, bonusNum)
        val result = calculator.calculate()

        val resultCount = result.groupingBy { it }.eachCount()
        OutputView.displayLottoResultTitle()
        LottoPrize.entries
            .reversed()
            .filter { it != LottoPrize.NOTHING }
            .forEach {
                val count = resultCount[it] ?: 0
                OutputView.displayLottoResult(it, count)
            }
        val prizeRatio = calculator.getTotalPriceRatio(result)
        OutputView.displayPrizeRatio(prizeRatio)

    }

}