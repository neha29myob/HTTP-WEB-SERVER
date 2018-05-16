package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class DefaultOkHandler implements HttpRequestHandler {

    @Override
    public Response handle(Request request) {
        Response response = new Response(200);

        if (!request.getRequestBody().equals("")) {
            response.setResponseBody(request.getRequestBody());
        }
        return response;
    }
}
