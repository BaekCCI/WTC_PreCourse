package lotto.model

class LottoCalculator(
    val purchasePrice: Int,
    val purchaseLotto: List<Lotto>,
    val winningLotto: Lotto,
    val bonusNum: Int,
) {

    fun calculate(): List<LottoPrize> {
        val lottoResult: MutableList<LottoPrize> = mutableListOf()
        purchaseLotto.forEach {
            lottoResult.add(isMatch(it.getLotto()))
        }
        return lottoResult
    }

    private fun isMatch(lotto: List<Int>): LottoPrize {
        val matchCount = lotto.intersect(winningLotto.getLotto()).size
        val isMatchBonus = lotto.contains(bonusNum)

        return LottoPrize.getRank(matchCount, isMatchBonus)
    }

    fun getTotalPriceRatio(lottoResult: List<LottoPrize>): Double {
        var sum = 0
        lottoResult.forEach {
            sum += it.prize
        }
        return sum.toDouble() / purchasePrice * 100
    }

}