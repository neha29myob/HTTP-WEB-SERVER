package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class RedirectHandler implements HttpRequestHandler {
    @Override
    public String handle(Request request) {
        Response response = new Response(302);

        response.setResponseHeader("Location","/");

        return response.toString();
    }
}
