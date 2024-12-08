package lotto.constant

enum class ErrorMsg(private val message: String) {
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해주세요."),
    THOUSAND_UNIT("천 단위로 입력해주세요."),
    INVALID_MATCHING("옳지 않은 매칭입니다."),
    NUMBER_OF_LOTTO("로또 번호는 6개여야 합니다."),
    LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_NUMBER("중복된 숫자가 존재합니다.");

    fun format(): String {
        return "[ERROR] $message"
    }
}