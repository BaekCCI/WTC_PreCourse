package racingcar

const val DELIMITER = ","

const val MAX_LENGTH = 5

object Validator {
    fun validateCar(input: String) {
        val carNames = input.split(DELIMITER).map { it.trim() }
        if (input == "") throw IllegalArgumentException("빈문자열이 입력되었습니다.")
        else if (carNames.size <= 1) throw IllegalArgumentException("자동차 이름은 2개 이상이여야 합니다.")
        else if (carNames.size != carNames.distinct().size) throw IllegalArgumentException("중복된 이름이 존재합니다.")
        else if (!checkCarNameSize(carNames)) throw IllegalArgumentException("자동차 이름은 1~5자여야 합니다.")
    }

    private fun checkCarNameSize(carNames: List<String>): Boolean {
        carNames.forEach {
            if (it.isEmpty() || it.length > MAX_LENGTH) return false
        }
        return true
    }

    fun validateCount(input: String) {
        val count = input.toIntOrNull()
        if (input == "") throw IllegalArgumentException("빈문자열이 입력되었습니다.")
        else if (count == null) throw IllegalArgumentException("정수로 입력해야 합니다.")
        else if (count <= 0) throw IllegalArgumentException("1회 이상 입력해야 합니다.")
    }
}