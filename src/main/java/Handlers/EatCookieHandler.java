package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class EatCookieHandler implements HttpRequestHandler {

    @Override
    public String handle(Request request) {
        Response response = new Response(200);
        String cookieValue = request.getRequestHeader().get("Cookie");
        response.setResponseBody("mmmm " + cookieValue);
        return response.toString();
    }
}
