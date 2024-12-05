package lotto

const val MONEY = "^(\\d+)|(\\d{1,3}(,\\d{3})*)$"

object Validator {
    fun moneyValidator(input: String) {
        require(input.matches(MONEY.toRegex())) { "[ERROR] 잘못된 입력입니다. 다시 입력해주세요." }
        require(input.replace(",", "").toInt() % 1_000 == 0) { "[ERROR] 천 단위로 입력해주세요." }
    }

    fun winningNumValidator(input: String) {
        val numbers = input.split(",").map { it.trim() }
        numbers.forEach {
            require(it.toIntOrNull() != null) { "[ERROR] 잘못된 입력입니다. 다시 입력해주세요." }
        }
    }

    fun bonusNumValidator(input: String) {
        require(input.toIntOrNull() != null) { "[ERROR] 잘못된 입력입니다. 다시 입력해주세요." }
    }
}