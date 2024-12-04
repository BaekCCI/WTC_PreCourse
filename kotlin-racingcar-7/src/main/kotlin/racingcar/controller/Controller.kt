package racingcar.controller

import racingcar.Validator
import racingcar.model.Car
import racingcar.model.Race
import racingcar.view.InputView
import racingcar.view.OutputView

const val DELIMITER = ","

class Controller {
    private val inputView = InputView()
    private val outputView = OutputView()
    fun start() {
        val cars = createCar()
        val count = getCount()
        raceStart(cars, count)
    }

    private fun createCar(): List<Car> {
        val input = inputView.getCar()
        Validator.validateCar(input)
        val carNames = input.split(DELIMITER).map { it.trim() }
        return carNames.map { Car(it, 0) }
    }

    private fun getCount(): Int {
        val input = inputView.getCount()
        Validator.validateCount(input)
        return input.toInt()
    }

    private fun raceStart(cars: List<Car>, count: Int) {
        val race = Race(cars)
        outputView.displayRaceTitle()
        for (i in 1..count) {
            race.startRace()
            outputView.displayRaceResult(cars)
        }
        val winners = race.getWinner()
        outputView.displayWinner(winners)
    }
}