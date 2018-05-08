import RequestHandler.HTTPRequestParser;
import RequestHandler.Request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class RequestResponseHandler implements Runnable{
    private Socket connection;

    public RequestResponseHandler(Socket client) {
        connection = client;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "Thread started:");
        try (Socket c = connection){
            processResponse(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public void processResponse(Socket connection) throws IOException {

        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        HTTPRequestParser requestParser = new HTTPRequestParser(input);
        Request request1 = requestParser.processRequest();

        if (request1.getPath().equalsIgnoreCase("/")) {
            String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "Today's Date is: " + new Date();
            connection.getOutputStream().write(httpResponse.getBytes("UTF-8"));
        } else if(request1.getPath().equalsIgnoreCase("/foobar")){
            connection.getOutputStream().write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes("UTF-8"));
        }
    }

}

