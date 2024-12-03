package calculator.model

const val CUSTOM_REGEX = "^//.+\\n*$"
const val NO_CUSTOM_REGEX = "^(\\d+(\\.\\d+)?)([,:]\\d+(\\.\\d+)?)*?$"

class Separator(val input : String){
    private var splitter = "[,:]"
    private lateinit var cleanedInput : String
    init{
        require(isValidInput()){"ERROR"}
    }
    private fun isValidInput() : Boolean{
        getCleanedInput()
        val regex = "^(\\d+(\\.\\d+)?)($splitter\\d+(\\.\\d+)?)*?$"
        if(cleanedInput == "") return true
        else if(cleanedInput.matches(regex.toRegex())) return true
        return false
    }
    private fun getCleanedInput(){
        cleanedInput = input
        if(input.matches(CUSTOM_REGEX.toRegex())){
            val startIndex = input.indexOf("//")+2
            val endIndex = input.indexOf("\\n")
            val customSplitter = input.substring(startIndex, endIndex)
            splitter = "[,:$customSplitter]"
            cleanedInput = input.substringAfter("\\n")
        }
    }
    fun getNumbers() : List<Float>{
        if(cleanedInput=="")return listOf(0f)
        return cleanedInput.split(splitter.toRegex()).map{it.toFloat()}
    }
}