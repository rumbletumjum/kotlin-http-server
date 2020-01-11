package httpserver

import java.io.IOException
import java.io.InputStream

class Request(private val input: InputStream) {
    var uri: String? = null
        private set

    fun parse() {
        val request = StringBuffer(2048)
        val i: Int
        val buffer = ByteArray(2048)
        i = try {
            input.read(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            -1
        }
        for (j in 0 until i) {
            request.append(buffer[j].toChar())
        }
        val requestString = request.toString()
        print(requestString)
        uri = parseUri(requestString)
    }

    private fun parseUri(requestString: String): String? {
        val index1: Int = requestString.indexOf(' ')
        val index2: Int
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1)
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2)
            }
        }
        return null
    }

}