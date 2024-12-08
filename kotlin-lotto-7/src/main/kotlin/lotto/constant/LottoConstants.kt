package lotto.constant

const val LOTTO_SIZE = 6
const val MAX_NUM = 45
const val MIN_NUM = 1
const val LOTTO_PRICE = 1_000
const val COMMA = ","
const val VALID_MONEY_PATTERN = "^(\\d+)|(\\d{1,3}(,\\d{3})*)$"

// ?<=\\d : Lookbehind, 앞에 숫자가 있는 위치
// ?=\\d{3} : Lookahead, 뒤에 숫자 3개가 있는 위치
// ?!\\d : negative Lookahead, 뒤에 숫자가 더이상 없어야 함.
const val THOUSAND_SEPARATOR_REGEX = "(?<=\\d)(?=(\\d{3})+(?!\\d))"