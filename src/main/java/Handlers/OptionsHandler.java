package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class OptionsHandler implements HttpRequestHandler {
    @Override
    public Response handle(Request request) {
        Response response = new Response(200);

        if (request.getPathName().equals("/method_options")) {
            response.setResponseHeader("Allow", "GET, HEAD, POST, OPTIONS, PUT");
            return response;
            //return "HTTP/1.1 200 OK\r\nAllow:GET, HEAD, POST, OPTIONS, PUT\r\n\r\n" + "Method option done";
        }
        response.setResponseHeader("Allow", "GET, OPTIONS, HEAD");
        return response;
    }
}
