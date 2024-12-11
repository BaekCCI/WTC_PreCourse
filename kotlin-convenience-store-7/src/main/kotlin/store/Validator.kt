package store

const val PURCHASE_FORMAT_REGEX =
    "^((\\[[ㄱ-ㅎ가-힣a-zA-Z0-9]+-\\d+\\])(,\\[[ㄱ-ㅎ가-힣a-zA-Z0-9]+-\\d+\\])*)|([ㄱ-ㅎ가-힣a-zA-Z0-9]+-\\d+)$"

enum class ErrorMessage(val message: String) {
    INVALID_PURCHASE_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    PRODUCT_NOT_FOUND("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    EXCEED_STOCK_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요.");

    fun format(): String {
        return "[ERROR] $message"
    }

}

class Validator {
    fun validatePurchaseInput(input: String) {
        val cleanedInput = input.replace(" ", "")
        require(cleanedInput.matches(PURCHASE_FORMAT_REGEX.toRegex())) { ErrorMessage.INVALID_PURCHASE_FORMAT.format() }
    }

    fun validateYesOrNoInput(input: String) {
        require(input == "y" || input == "n")
    }
}