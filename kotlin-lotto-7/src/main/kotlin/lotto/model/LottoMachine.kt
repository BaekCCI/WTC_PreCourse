package lotto.model

import camp.nextstep.edu.missionutils.Randoms
import lotto.constant.*

class LottoMachine(private val money: Int) {
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
        return Randoms.pickUniqueNumbersInRange(MIN_NUM, MAX_NUM, LOTTO_SIZE).sorted()
    }

    fun getPurchasedLotto(): List<Lotto> {
        return purchasedLotto
    }
}