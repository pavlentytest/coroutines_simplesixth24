import kotlinx.coroutines.*

suspend fun main2() {
    for(i in 0..5) {
        delay(500)
        println(i)
    }
    println("Test message!")
}
suspend fun main3() {
    coroutineScope {
        launch {
            // логика
            heavyWork()
        }
        println("Test message!")
    }
}
suspend fun heavyWork() {
    for(i in 0..5) {
        delay(500)
        println(i)
    }
}

suspend fun main4() {
    coroutineScope {
        val job: Job = launch {
            heavyWork()
        }
        println("Start!")
        job.join() // дождаться завершения работы корутины
        println("Finished!")
    }
}

suspend fun main5() {
    coroutineScope {
        // корутина создается, но не запускается
        val job: Job = launch(start = CoroutineStart.LAZY) {
            delay(1000)
            println("Coroutine started!")
        }
        delay(2000)
        job.start() // корутина запущена
        delay(4000)
        job.cancel() // отменяем корутину
        job.join() // ждем завершения
        job.cancelAndJoin()
    }
}

suspend fun main6() {
    coroutineScope {
        val result : Deferred<String> = async {
            getResult()
        }
        println("result: ${result.await()}")
    }
}
suspend fun getResult() : String {
    delay(1000)
    return "Result string!"
}
suspend fun main() {
    coroutineScope {
        val result : Deferred<String> = async(start = CoroutineStart.LAZY) {
            getResult()
        }
        delay(2000)
        result.start() // запуск
        println("result: ${result.await()}")
    }
}


