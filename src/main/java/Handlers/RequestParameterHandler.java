package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

import java.util.Map;

public class RequestParameterHandler implements HttpRequestHandler {
    @Override
    public String handle(Request request) {
        Response response = new Response(200);

        Map<String, String> queryPairs = request.getSearchQuery();

        String body = "";
        for(String headerKey: queryPairs.keySet()){
            body+= headerKey + " = " + queryPairs.get(headerKey) + "\r\n";
        }
        response.setResponseBody(body);
        return response.toString();
    }
}
