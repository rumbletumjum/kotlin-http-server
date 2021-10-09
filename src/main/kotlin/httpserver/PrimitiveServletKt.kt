package httpserver

import java.io.IOException
import javax.servlet.*

class PrimitiveServletKt : Servlet {
    @Throws(ServletException::class)
    override fun init(config: ServletConfig) {
        println("init")
    }

    @Throws(ServletException::class, IOException::class)
    override fun service(req: ServletRequest, res: ServletResponse) {
        println("service")
        val out = res.writer
        out.println("Hello: Roses are red")
        out.println("Violets are blue")
    }

    override fun destroy() {
        println("destroy")
    }

    override fun getServletInfo(): String {
        return "foo"
    }

    override fun getServletConfig(): ServletConfig? {
        return null
    }
}