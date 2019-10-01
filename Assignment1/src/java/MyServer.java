
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetSocketAddress;

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
        
        String path = exchange.getRequestURI().getPath();
        if(path.equals("/")) path = "index1.html";
        String response = "";
        try(FileInputStream input = new FileInputStream("C:\\Users\\Jean-Loup\\Documents\\NetBeansProjects\\Assignment1\\web\\"+path)) {

        int data = input.read();
        while(data != -1){
            response += (char) data;
            data = input.read();
        }
    }
      
        catch(FileNotFoundException e){
                exchange.sendResponseHeaders(404, response.getBytes().length);
        };
        
        if (path.substring(path.length()-4).equals("html"))
        {
            Headers headers = exchange.getResponseHeaders();
             headers.add("Content-Type", "text/html");
             exchange.sendResponseHeaders(200, response.getBytes().length);
        }
        
        else if (path.substring(path.length()-3).equals("css"))
        {
            Headers headers = exchange.getResponseHeaders();
             headers.add("Content-Type", "text/css");
             exchange.sendResponseHeaders(200, response.getBytes().length);
        }
        //response code and content length
        
        
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
}

    private static Reader NewFileReader(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
