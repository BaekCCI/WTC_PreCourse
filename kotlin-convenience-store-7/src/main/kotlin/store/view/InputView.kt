package store.view

import camp.nextstep.edu.missionutils.Console

enum class InputMessage(private val message: String) {
    PURCHASE_ITEM("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    ADD_EXTRA_ITEM("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    NON_DISCOUNT_ITEM("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    MEMBERSHIP_DISCOUNT("멤버십 할인을 받으시겠습니까? (Y/N)"),
    ADDITIONAL_PURCHASE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");

    fun format(vararg args: Any): String {
        return message.format(*args)
    }
}

class InputView {
    fun purchaseItem(): String {
        println(InputMessage.PURCHASE_ITEM.format())
        return Console.readLine()
    }

    fun askAddPromotionItem(item: String): String {
        println(InputMessage.ADD_EXTRA_ITEM.format(item))
        return Console.readLine()
    }

    fun askForNonDiscountedItems(item: String, amount: Int): String {
        println(InputMessage.NON_DISCOUNT_ITEM.format(item, amount))
        return Console.readLine()
    }

    fun askForMembershipDiscount(): String {
        println(InputMessage.MEMBERSHIP_DISCOUNT.format())
        return Console.readLine()
    }

    fun askForAdditionalPurchase(): String {
        println(InputMessage.ADDITIONAL_PURCHASE.format())
        return Console.readLine()
    }


}