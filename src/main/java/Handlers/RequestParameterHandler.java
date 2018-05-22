package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

import java.util.Map;
import java.util.stream.Collectors;

public class RequestParameterHandler implements HttpRequestHandler {
    private String CRLF = "\r\n";

    @Override
    public Response handle(Request request) {
        Response response = new Response(200);
        Map<String, String> queryPairs = request.getSearchQuery();

        String body = queryPairs.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue() + CRLF)
                .collect(Collectors.joining(""));

        response.setResponseBody(body);
        return response;
    }
}
