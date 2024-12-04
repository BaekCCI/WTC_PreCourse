package racingcar.model

import camp.nextstep.edu.missionutils.Randoms

const val MOVING_FORWARD: Int = 4
const val STOP: Int = 3

class Race(private val cars: List<Car>) {
    fun startRace() {
        cars.forEach {
            if (isGoForward()) it.score++
        }
    }

    private fun isGoForward(): Boolean {
        val randomNum = Randoms.pickNumberInRange(0, 9)
        return randomNum >= MOVING_FORWARD
    }

    fun getWinner(): List<Car> {
        val maxScore = cars.maxOf { it.score }
        return cars.filter { it.score == maxScore }
    }
}