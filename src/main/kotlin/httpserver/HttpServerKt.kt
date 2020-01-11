package httpserver

import java.io.File
import java.io.IOException
import java.net.InetAddress
import java.net.ServerSocket
import kotlin.system.exitProcess

class HttpServerKt {
    private val WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot"
    private val SHUTDOWN_COMMAND = "/SHUTDOWN"
    private var shutdown = false
    
    fun main(args: Array<String>) {
        val server = HttpServerKt()
        server.await()
    }

    fun await() {
        val port = 8080
        try {
            val serverSocket = ServerSocket(port, 1, InetAddress.getLocalHost())
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(1)
        }
    }
}