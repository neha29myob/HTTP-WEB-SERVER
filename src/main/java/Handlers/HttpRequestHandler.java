package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public interface HttpRequestHandler {

    String handle(Request request);

}
