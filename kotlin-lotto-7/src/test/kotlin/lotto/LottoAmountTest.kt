package lotto

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import lotto.controller.Controller
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoAmountTest : NsTest() {

    @Test
    fun `단위 구분 쉼표의 위치가 적절치 않은 경우 예외 발생`() {
        assertSimpleTest {
            runException("10,00")
            assertThat(output()).contains(ERROR_MESSAGE)
        }
        assertSimpleTest {
            runException("100,0")
            assertThat(output()).contains(ERROR_MESSAGE)
        }
    }

    @Test
    fun `입력값이 0인 경우 예외 발생`() {
        assertSimpleTest {
            runException("0")
            assertThat(output()).contains(ERROR_MESSAGE)
        }
    }

    @Test
    fun `입력값이 1_000으로 나누어 떨어지지 않는 경우 예외 발생`() {
        assertSimpleTest {
            runException("1500")
            assertThat(output()).contains(ERROR_MESSAGE)
        }
        assertSimpleTest {
            runException("1,500")
            assertThat(output()).contains(ERROR_MESSAGE)
        }
    }

    override fun runMain() {
        main()
    }

    companion object {
        const val ERROR_MESSAGE = "[ERROR]"
    }
}