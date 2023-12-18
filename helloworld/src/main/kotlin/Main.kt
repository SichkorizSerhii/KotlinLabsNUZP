import com.diacht.ktest.compose.startTestUi
import me.ssi.helloworld.BuildConfig
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.abs
import kotlin.math.sin

fun seed(): String = "SichkorizSerhii"


fun labNumber() : Int = BuildConfig.LAB_NUMBER

fun iCalculate(x0: Int = -55, x1: Int = -16, x2: Int = -70, x3: Int = 99): Double {
    val maxAbs = max(abs(x0), max(abs(x1), max(abs(x2), abs(x3))))
    return cos(maxAbs.toDouble())
}

fun dCalculate(x0: Double = -57.33, x1: Double = -2.56, x2: Double = 9.02, x3: Double = 28.75): Double {
    val maxAbs = max(abs(x0), max(abs(x1), max(abs(x2), abs(x3))))
    return sin(maxAbs)
}

fun strCalculate(x0: String, x1: String): Int {
    require(x0.length == x1.length)

    var differences = 0
    for (i in x0.indices) {
        val charX0 = x0[i]
        val charX1 = x1[i]

        if ((charX0 == 'T' && charX1 == 'C') || (charX0 == 'C' && charX1 == 'T')) {
            differences += 2
        } else if ((charX0 == 'T' || charX0 == 'C') && (charX1 == 'T' || charX1 == 'C')) {
            differences++
        }
    }

    return differences
}

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    val result = iCalculate()
    println("Результат розрахунку Завдання №1 sCalculate() = $result")

    val dResult = dCalculate()
    println("Результат розрахунку Завдання №2 dCalculate() = $dResult")

    val strResult = strCalculate("ATGCJ", "TAGCT")
    println("Результат розрахунку Завдання №3 strCalculate() = $strResult")


    startTestUi(seed(), labNumber())
}


