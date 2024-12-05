package lotto.model

import camp.nextstep.edu.missionutils.Randoms

const val LOTTO_PRICE = 1_000

class LottoMachine(val money: Int) {
    private val amount: Int = money / LOTTO_PRICE
    private val purchasedLotto = generateLotto()

    private fun generateLotto(): List<Lotto> {
        val purchasedLotto: MutableList<Lotto> = mutableListOf()
        for (i in 1..amount) {
            purchasedLotto.add(Lotto(getRandomNum()))
        }
        return purchasedLotto
    }

    private fun getRandomNum(): List<Int> {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6)
    }

    fun getPurchasedLotto(): List<Lotto> {
        return purchasedLotto
    }
}