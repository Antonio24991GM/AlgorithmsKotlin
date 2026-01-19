import kotlin.math.pow
object ArmstrongNumber {

    fun check(input: Int): Boolean {
        val size = input.toString().length.toDouble()
        var number = input
        var total = 0
        while (number > 0) {
            total += Math.pow((number % 10).toDouble(), size).toInt()
            number /= 10
        }
        return total == input
    }
}
