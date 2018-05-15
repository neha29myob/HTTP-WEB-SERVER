package HttpResponse;

import Handlers.*;
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
        } catch (IOException e) {
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

        Router router = new Router(request);

        router.addRoute("/form", new DefaultOkHandler());
        router.addRoute("/put-target", new DefaultOkHandler());
        router.addRoute("/method_options", new OptionsHandler());
        router.addRoute("/method_options2", new OptionsHandler());
        router.addRoute("/redirect", new RedirectHandler());
        router.addRoute("/coffee", new FourEighteenHandler());
        router.addRoute("/tea", new DefaultOkHandler());
        router.addRoute("/parameters", new RequestParameterHandler());
        router.addRoute("/cookie", new GetCookieHandler());
        router.addRoute("/eat_cookie", new EatCookieHandler());
        router.addRoute("/logs", new AuthHandler());
        router.addRoute("/", new DirectoryHandler());

        Response httpResponse = router.getResponse(request);

       OutputStream outputStream = connection.getOutputStream();
       // outputStream.write(httpResponse.toString().getBytes());
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

