package httpserver;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PrimitiveServlet implements Servlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    System.out.println("service");
    var out = res.getWriter();
    out.println("Hello: Roses are red");
    out.println("Violets are blue");
  }

  @Override
  public void destroy() {
    System.out.println("destroy");
  }

  @Override
  public String getServletInfo() {
    return "foo";
  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }
}
