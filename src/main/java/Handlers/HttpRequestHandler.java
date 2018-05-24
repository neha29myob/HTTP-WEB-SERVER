package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public interface HttpRequestHandler {
    Response handle(Request request);
}
