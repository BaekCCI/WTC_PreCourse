package lotto

import lotto.constant.*

object Validator {
    fun moneyValidator(input: String) {
        require(input.matches(VALID_MONEY_PATTERN.toRegex())) { ErrorMsg.INVALID_INPUT.format() }
        val inputWithOutComma = input.replace(COMMA, "").toInt()
        require(inputWithOutComma != 0 && inputWithOutComma % LOTTO_PRICE == 0) { ErrorMsg.THOUSAND_UNIT.format() }
    }

    fun winningNumValidator(input: String) {
        val numbers = input.split(COMMA).map { it.trim() }
        numbers.forEach {
            require(it.toIntOrNull() != null) { ErrorMsg.INVALID_INPUT.format() }
        }
    }

    fun bonusNumValidator(input: String) {
        require(input.toIntOrNull() != null) { ErrorMsg.INVALID_INPUT.format() }
    }
}