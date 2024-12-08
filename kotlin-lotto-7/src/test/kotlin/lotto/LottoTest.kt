package lotto

import lotto.model.Lotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @Test
    fun `로또 번호의 개수가 6개 미만일 경우 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1))
        }
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3))
        }
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @Test
    fun `로또 번호가 범위를 벗어나면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 46, 5))
        }
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(0, 2, 3, 4, 45, 5))
        }
    }

    @Test
    fun `보너스 번호가 범위를 벗어나면 예외 발생`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThrows<IllegalArgumentException> {
            lotto.isDuplicate(46)
        }
        assertThrows<IllegalArgumentException> {
            lotto.isDuplicate(0)
        }
    }

    @Test
    fun `보너스 번호가 로또 번호와 중복되면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
            lotto.isDuplicate(3)
        }
    }
}
