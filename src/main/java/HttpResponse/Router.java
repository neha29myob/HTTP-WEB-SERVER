package HttpResponse;

import Handlers.FileContentHandler;
import Handlers.HttpRequestHandler;
import HttpRequest.Request;

import java.util.HashMap;

public class Router {

    HashMap<String, HttpRequestHandler> routeMap = new HashMap<>();
    private Request request;


    public Router(Request request) {
        this.request = request;
    }

    public void addRoute(String path, HttpRequestHandler handler) {
        routeMap.put(path, handler);
    }

    public Response getResponse(Request request) {

        return routeMap.getOrDefault(request.getPathName(), new FileContentHandler()).handle(request);
    }
}
