import com.diacht.ktest.compose.startTestUi
import me.ssi.helloworld.BuildConfig
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.math.sqrt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

fun seed(): String = "SichkorizSerhii"

fun labNumber() : Int = BuildConfig.LAB_NUMBER

suspend fun getNumberFromServer(message: String): Int {
    return withContext(Dispatchers.IO) {
        val url = URL("http://diacht.2vsoft.com/api/send-number?message=$message")
        val connection = url.openConnection()
        connection.connect()
        val input = connection.getInputStream()
        val buffer = ByteArray(128)
        val bytesRead = input.read(buffer)
        input.close()
        String(buffer, 0, bytesRead).toInt()
    }
}

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {
    val numbers = strList.map { async { getNumberFromServer(it) } }
    val sumOfSquares = numbers.sumOf { number ->
        val value = number.await().toDouble()
        value * value
        
    }
    sqrt(sumOfSquares)
}


suspend fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")



    startTestUi(seed(), labNumber())
}


