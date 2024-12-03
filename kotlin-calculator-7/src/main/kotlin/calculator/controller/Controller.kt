package calculator.controller

import calculator.model.Separator
import calculator.view.InputView
import camp.nextstep.edu.missionutils.Console

class Controller {

    fun start(){
        val inputView = InputView()
        val input = inputView.getInput()
        val separator = Separator(input)
        val numbers = separator.getNumbers()
        println(numbers)
    }
}g