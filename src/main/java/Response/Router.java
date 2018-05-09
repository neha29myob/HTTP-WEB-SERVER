package Response;

import Handlers.HttpRequestHandler;
import RequestHandler.Request;

import java.util.HashMap;

public class Router {

    private Request request;

    //HashMap<Request, HttpRequestHandler> routeMap = new HashMap<>();
    HashMap<String , HttpRequestHandler> routeMap = new HashMap<>();

    //HashMap<Map<RequestMethod,String>, HttpRequestHandler> routeMap = new HashMap<>();


    public Router(Request request) {
        this.request = request;
    }

    public void addRoute(String path, HttpRequestHandler handler) {
        routeMap.put(path,handler);
    }


    public String getResponse(Request request){

        if(routeMap.containsKey(request.getPath())){
            System.out.println(request.getRequestMethod() + " " + request.getPath());
            return routeMap.get(request.getPath()).handle(request);
        }
        return "HTTP/1.1 404 Not Found\r\n\r\n";

    }
}

