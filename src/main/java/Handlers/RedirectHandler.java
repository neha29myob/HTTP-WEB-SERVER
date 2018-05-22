package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class RedirectHandler implements HttpRequestHandler {
    @Override
    public Response handle(Request request) {
        Response response = new Response(302);
        response.setResponseHeader("Location", "/");
        return response;
    }
}
