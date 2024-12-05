package lotto.model

const val MAX_NUM = 45
const val MIN_NUM = 1

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "[ERROR] 로또 번호는 6개여야 합니다." }
        require(isInRange()) { "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다." }
        require(isDuplicate()) { "{ERROR] 중복된 숫자가 존재합니다." }
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
        require(bonusNum in MIN_NUM..MAX_NUM) { "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다." }
        require(!numbers.contains(bonusNum)) { "{ERROR] 중복된 숫자가 존재합니다." }
    }

}
