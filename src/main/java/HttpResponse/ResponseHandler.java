package HttpResponse;

import HttpRequest.HTTPRequestParser;
import HttpRequest.HttpRequestReader;
import HttpRequest.Request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ResponseHandler implements Runnable {
    private Socket connection;
    public static List<String> logs = new ArrayList<>();

    public ResponseHandler(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "Thread started:");
        try (Socket c = connection) {
            processResponse(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processResponse(Socket connection) throws IOException {

        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        HttpRequestReader requestReader = new HttpRequestReader(input);
        String requestString = requestReader.readRequest();
        HTTPRequestParser requestParser = new HTTPRequestParser();
        Request request = requestParser.parseRequest(requestString);
        logs.add(requestString.split("\r\n")[0]);

        Router router = new Router();

        Response httpResponse = router.getResponse(request);

        OutputStream outputStream = connection.getOutputStream();
        writeResponse(httpResponse, outputStream);

    }

    private void writeResponse(Response httpResponse, OutputStream outputStream) throws IOException {
        outputStream.write(httpResponse.getResponseStatusLine().getBytes());
        outputStream.write(httpResponse.getResponseHeader().getBytes());
        outputStream.write(httpResponse.getResponseBody());
        outputStream.flush();
        outputStream.close();
    }

}

