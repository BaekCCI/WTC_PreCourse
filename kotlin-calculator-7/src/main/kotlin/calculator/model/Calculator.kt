package calculator.model

class Calculator {
    fun sum(numbers : List<Float>) : Float{
        var result =0f
        numbers.forEach {
            result+=it
        }
        return result
    }
}