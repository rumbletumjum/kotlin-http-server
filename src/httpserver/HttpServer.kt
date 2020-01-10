package httpserver

import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import kotlin.system.exitProcess

class HttpServer {
    private var shutdown = false
    fun await() {
        val port = 8080
        val serverSocket: ServerSocket
        try {
            serverSocket = ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"))
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(1)
        }
        while (!shutdown) {
            var socket: Socket
            var `is`: InputStream
            var os: OutputStream
            try {
                socket = serverSocket.accept()
                `is` = socket.getInputStream()
                os = socket.getOutputStream()
                val request = Request(`is`)
                request.parse()
                val response = Response(os)
                response.request = request
                response.sendStaticResource()
                socket.close()
                shutdown = request.uri == SHTUDOWN_COMMAND
            } catch (e: IOException) {
                e.printStackTrace()
                continue
            }
        }
    }

    companion object {
        val WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot"
        private const val SHTUDOWN_COMMAND = "/SHUTDOWN"
        @JvmStatic
        fun main(args: Array<String>) {
            val server = HttpServer()
            server.await()
        }
    }
}