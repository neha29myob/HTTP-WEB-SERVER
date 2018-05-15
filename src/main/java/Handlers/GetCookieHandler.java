package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class GetCookieHandler implements HttpRequestHandler {
    @Override
    public String handle(Request request) {
        Response response = new Response(200);
        response.setResponseBody("Eat");
        String cookieValue = request.getSearchQuery().values().stream().findFirst().get();
        response.setResponseHeader("Set-Cookie", cookieValue);
        return response.toString();
    }
}
