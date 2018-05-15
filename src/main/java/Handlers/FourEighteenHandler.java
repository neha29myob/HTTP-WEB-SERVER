package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class FourEighteenHandler implements HttpRequestHandler {
    @Override
    public Response handle(Request request) {
        Response response = new Response(418);
        response.setResponseBody("I'm a teapot");
        return response;
    }
}
