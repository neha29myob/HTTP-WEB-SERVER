package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class OptionsHandler implements HttpRequestHandler {

    private String ALL_OPTIONS = "GET, HEAD, POST, OPTIONS, PUT";
    private String FEW_OPTIONS = "GET, OPTIONS, HEAD";

    @Override
    public Response handle(Request request) {
        Response response = new Response(200);

        if (request.getPathName().equals("/method_options")) {
            response.setResponseHeader("Allow", ALL_OPTIONS);
            return response;
        }
        response.setResponseHeader("Allow", FEW_OPTIONS);
        return response;
    }
}
