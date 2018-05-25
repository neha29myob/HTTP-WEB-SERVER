package HttpServer;

import HttpRequest.Request;
import HttpRequest.RequestParser;
import HttpRequest.RequestReader;
import HttpResponse.Response;
import HttpResponse.ResponseWriter;
import HttpServer.Router;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RequestResponseProcessor implements Runnable {
    private Socket connection;
    private Router router;
    public static List<String> logs = new ArrayList<>();

    public RequestResponseProcessor(Socket connection, Router router) {
        this.connection = connection;
        this.router = router;
    }

    public void run() {
        try (Socket socket = connection) {
            processResponse(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processResponse(Socket connection) throws IOException {

        String requestString = RequestReader.readRequest(new InputStreamReader(connection.getInputStream()));
        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(requestString);
        Response httpResponse = router.getResponse(request);
        ResponseWriter.writeResponse(httpResponse, connection.getOutputStream());

        logs.add(requestString.split("\r\n")[0]);
    }
}

