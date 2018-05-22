package HttpResponse;

import HttpRequest.Request;
import HttpRequest.RequestParser;
import HttpRequest.RequestReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ResponseHandler implements Runnable {
    public static List<String> logs = new ArrayList<>();
    private Socket connection;
    private Router router;

    public ResponseHandler(Socket connection, Router router) {
        this.connection = connection;
        this.router = router;
    }

    public void run() {
        try (Socket c = connection) {
            processResponse(c);
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

