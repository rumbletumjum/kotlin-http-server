package httpserver

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.OutputStream

class Response(private var output: OutputStream) {
    var request: Request? = null


    @Throws(IOException::class)
    fun sendStaticResource() {
        val bytes = ByteArray(BUFFER_SIZE)
        var fis: FileInputStream? = null
        try {
            val file = File(HttpServer.WEB_ROOT, request!!.uri)
            if (file.exists()) {
                fis = FileInputStream(file)
                var ch = fis.read(bytes, 0, BUFFER_SIZE)
                while (ch != -1) {
                    output.write(bytes, 0, ch)
                    ch = fis.read(bytes, 0, BUFFER_SIZE)
                }
            } else {
                val errorMessage = ("HTTP/1.1 404 File Not Found\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: 23\r\n"
                        + "\r\n"
                        + "<h1>File Not Found</h1>")
                output.write(errorMessage.toByteArray())
            }
        } catch (e: Exception) {
            println(e.toString())
        } finally {
            fis?.close()
        }
    }

    companion object {
        private const val BUFFER_SIZE = 1024
    }

}