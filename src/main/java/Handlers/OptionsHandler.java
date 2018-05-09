package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class OptionsHandler implements HttpRequestHandler {
    @Override
    public String handle(Request request) {

        System.out.println("Method"+ request.getPath());


        if(request.getPath().equals("/method_options")){
            System.out.println("Method options");

            Response response = new Response(200);

            response.setResponseHeader("Allow","GET, HEAD, POST, OPTIONS, PUT");

            return response.toString();

            //return "HTTP/1.1 200 OK\r\nAllow:GET, HEAD, POST, OPTIONS, PUT\r\n\r\n" + "Method option done";
        }
        return "HTTP/1.1 200 OK\r\nAllow:GET, OPTIONS, HEAD\r\n\r\n" + "Method2 options done";
        }
}
