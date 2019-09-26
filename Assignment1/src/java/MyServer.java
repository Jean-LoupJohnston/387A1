
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jean-Loup
 */
public class MyServer  {
    
    public static void main (String[] args) throws IOException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(7900),0);
        HttpContext context = server.createContext("/");
        context.setHandler(MyServer::handleRequest);
        server.start();
    }
    
    private static void handleRequest(HttpExchange exchange) throws IOException {
        String response = "Hi there!";
        //response code and content length
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
}

    
}
