import RequestHandler.HTTPRequestParser;
import RequestHandler.Request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleHttpServer {

    private int port;

    public SimpleHttpServer(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket connection = serverSocket.accept()) {

                    InputStreamReader input = new InputStreamReader(connection.getInputStream());
                    HTTPRequestParser requestParser = new HTTPRequestParser(input);
                    //String request = requestParser.readInput();
                    //System.out.println(request);
                    Request request1 = requestParser.processRequest();

                    if(request1.getPath().equalsIgnoreCase("/")){
                        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "Today's Date is: "+ new Date();
                        connection.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                    }
                    else{
                        connection.getOutputStream().write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes("UTF-8"));
                    }
                }
            }
        }
    }
}
