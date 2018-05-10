package HttpResponse;

import Handlers.HttpRequestHandler;
import HttpRequest.Request;

import java.util.HashMap;

public class Router {

    private Request request;
    HashMap<String, HttpRequestHandler> routeMap = new HashMap<>();


    public Router(Request request) { this.request = request;}

    public void addRoute(String path, HttpRequestHandler handler) {
        routeMap.put(path, handler);
    }

    public String getResponse(Request request) {
        if (routeMap.containsKey(request.getPathName())) {
            System.out.println("Router pathName " + request.getRequestMethod() + " " + request.getPathName());
            return routeMap.get(request.getPathName()).handle(request);
        }
        return "HTTP/1.1 404 Not Found\r\n\r\n";
    }
}