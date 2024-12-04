package racingcar.controller

import racingcar.Validator
import racingcar.model.Car
import racingcar.view.InputView

const val DELIMITER = ","

class Controller {
    private val inputView = InputView()
    fun start() {
        val cars = createCar()

    }

    private fun createCar(): List<Car> {
        val input = inputView.getCar()
        Validator.validateCar(input)
        val carNames = input.split(DELIMITER).map { it.trim() }
        return carNames.map { it -> Car(it, 0) }
    }
}