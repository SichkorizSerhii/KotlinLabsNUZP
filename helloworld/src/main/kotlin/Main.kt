import com.diacht.ktest.compose.startTestUi
import me.ssi.helloworld.BuildConfig
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.math.sqrt
import java.net.URL

fun seed(): String = "SichkorizSerhii"

fun labNumber() : Int = BuildConfig.LAB_NUMBER

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {
    val deferredResults = strList.map { str ->
        async {
            val url = "http://diacht.2vsoft.com/api/send-number?message=$str"
            val response = URL(url).readText().toDouble()
            response
        }
    }

    val results = deferredResults.awaitAll()
    sqrt(results.map { it * it }.sum())
}

suspend fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    val strList = listOf("x0", "x1", "x2", "x3", "x4", "x5", "x6")

    val result = serverDataCalculate(strList)
    println("Результат обчислення: $result")

    startTestUi(seed(), labNumber())
}


