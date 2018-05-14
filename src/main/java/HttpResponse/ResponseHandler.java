package HttpResponse;

import Handlers.*;
import HttpRequest.HTTPRequestParser;
import HttpRequest.HttpRequestReader;
import HttpRequest.Request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ResponseHandler implements Runnable {
    private Socket connection;

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
        //CrudHandler crudHandler = new CrudHandler();
        //router.addRoute("/cat-form", crudHandler);
        //router.addRoute("/cat-form/data", crudHandler);
        router.addRoute("/", new DirectoryHandler());



        String httpResponse = router.getResponse(request);

        connection.getOutputStream().write(httpResponse.getBytes());





//        router.addRoute(RequestMethod.GET, "/method_options", new OptionsHandler());
//        router.addRoute(RequestMethod.PUT, "/method_options", new OptionsHandler());
//        router.addRoute(RequestMethod.POST, "/method_options", new OptionsHandler());
//        router.addRoute(RequestMethod.GET, "/method_options2", new OptionsHandler());


       // if (request.getPath().equalsIgnoreCase("/")) {
            //String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "Today's Date is: " + new Date();
//        } else if (request.getPath().equalsIgnoreCase("/foobar")) {
//            connection.getOutputStream().write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes("UTF-8"));
//        } else {
//            connection.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes("UTF-8"));
//
        }

}

