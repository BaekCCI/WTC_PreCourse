package racingcar.view

import racingcar.model.Car

class OutputView {
    fun displayRaceTitle() {
        print("실행 결과")
    }

    fun displayRaceResult(cars: List<Car>) {
        println()
        cars.forEach {
            println("${it.name} : ${"-".repeat(it.score)}")
        }
    }

    fun displayWinner(cars: List<Car>) {
        println("")
        print("최종 우승자 : ${cars.joinToString(", ") { it.name }}")
    }
}