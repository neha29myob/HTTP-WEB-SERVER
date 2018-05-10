package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

import java.util.Map;

public class RequestParameterHandler implements HttpRequestHandler {
    @Override
    public String handle(Request request) {
        Response response = new Response(200);

        System.out.println("handlerRequest "+ request.getSearchRequest());

        Map<String, String> queryPairs = request.getSearchRequest();

        String body = "";
        for(String headerKey: queryPairs.keySet()){
            body+= headerKey + "=" + queryPairs.get(headerKey) + "\r\n";
        }
        System.out.println("Body " + body);
        response.setResponseBody(body);
        return response.toString();
    }
}
