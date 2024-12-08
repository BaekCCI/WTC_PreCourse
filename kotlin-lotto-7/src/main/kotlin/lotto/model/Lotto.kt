package lotto.model

import lotto.constant.*

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == LOTTO_SIZE) { ErrorMsg.NUMBER_OF_LOTTO.format() }
        require(isInRange()) { ErrorMsg.LOTTO_NUMBER_RANGE.format() }
        require(isDuplicate()) { ErrorMsg.DUPLICATE_NUMBER.format() }
    }

    private fun isInRange(): Boolean {
        numbers.forEach {
            if (it < MIN_NUM || it > MAX_NUM) return false
        }
        return true
    }

    private fun isDuplicate(): Boolean {
        return numbers.size == numbers.distinct().size
    }

    fun isDuplicate(bonusNum: Int) {
        require(bonusNum in MIN_NUM..MAX_NUM) { ErrorMsg.LOTTO_NUMBER_RANGE.format() }
        require(!numbers.contains(bonusNum)) { ErrorMsg.DUPLICATE_NUMBER.format() }
    }

    fun getLotto(): List<Int> {
        return numbers
    }
}
