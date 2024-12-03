package calculator.view

class OutputView {
    fun display(result : Float){
        if(result%1 == 0f){
            println("결과 : ${result.toInt()}")
        }else{
            println("결과 : $result")
        }
    }
}