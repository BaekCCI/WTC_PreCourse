package calculator.controller

import calculator.model.Calculator
import calculator.model.Separator
import calculator.view.InputView
import calculator.view.OutputView

class Controller {

    fun start() {
        val inputView = InputView()
        val input = inputView.getInput()
        val separator = Separator(input)
        val numbers = separator.getNumbers()
        println(numbers)
        val calculator = Calculator()
        val result = calculator.sum(numbers)
        val outputView = OutputView()
        outputView.display(result)
    }
}