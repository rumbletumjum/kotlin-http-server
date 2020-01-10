package httpserver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
  public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

  private static final String SHTUDOWN_COMMAND = "/SHUTDOWN";

  private boolean shutdown = false;

  public static void main(String[] args) {
    HttpServer server = new HttpServer();
    server.await();
  }

  public void await() {
    int port = 8080;
    ServerSocket serverSocket = null;

    try {
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    while (!shutdown) {
      Socket socket;
      InputStream is;
      OutputStream os;

      try {
        socket = serverSocket.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();

        Request request = new Request(is);
        request.parse();

        Response response = new Response(os);
        response.setRequest(request);
        response.sendStaticResource();

        socket.close();

        shutdown = request.getUri().equals(SHTUDOWN_COMMAND);
      } catch (IOException e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
