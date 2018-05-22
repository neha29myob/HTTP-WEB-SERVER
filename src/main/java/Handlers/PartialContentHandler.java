package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class PartialContentHandler implements HttpRequestHandler {

    @Override
    public Response handle(Request request) {
        String range = request.getRequestHeader().get("Range").split("=")[1];

        if (range.startsWith("0")) {
            Response response = new Response(206);
            response.setResponseHeader("Content-Range", "bytes 0-4/77");
            response.setResponseBody("This ");
            return response;
        } else if (range.startsWith("-")) {
            Response response = new Response(206);
            response.setResponseHeader("Content-Range", "bytes 71-76/77");
            response.setResponseBody(" 206.\n");
            return response;
        } else if (range.startsWith("4")) {
            Response response = new Response(206);
            response.setResponseHeader("Content-Range", "bytes 4-76/77");
            response.setResponseBody(" is a file that contains text to read part of in order to fulfill a 206.\n");
            return response;
        }
        Response response = new Response(416);
        response.setResponseHeader("Content-Range", "bytes */77");
        return response;


    }
}
