package Handlers;

import RequestHandler.Request;

import java.util.Date;

public class OptionsHandler implements HttpRequestHandler {
    @Override
    public String handle(Request request) {

        System.out.println("Method"+ request.getPath());

        if(request.getPath().equals("/method_options")){
            System.out.println("Method options");
            return "HTTP/1.1 200 OK\r\nAllow:GET, HEAD, POST, OPTIONS, PUT\r\n\r\n" + "Method option done";
        }
        return "HTTP/1.1 200 OK\r\nAllow:GET, OPTIONS, HEAD\r\n\r\n" + "Method2 options done";
        }
}
