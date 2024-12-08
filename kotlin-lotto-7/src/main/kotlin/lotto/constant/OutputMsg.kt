package lotto.constant

enum class OutputMsg(private val message: String) {
    PURCHASED_LOTTO("%d개를 구매했습니다."),
    WINNING_STATISTICS_TITLE("당첨 통계\n---"),
    WINNING_STATISTICS_BONUS("%d개 일치, 보너스 볼 일치 (%s원) - %d개"),
    WINNING_STATISTICS("%d개 일치 (%s원) - %d개"),
    TOTAL_PRIZE_RATIO("총 수익률은 %.1f%%입니다.");

    fun format(vararg args: Any): String {
        return message.format(*args)
    }
}