package HttpServer;

import Handlers.*;
import HttpRequest.Request;
import HttpResponse.Response;

import java.util.HashMap;

public class Router {

    HashMap<String, HttpRequestHandler> routeMap = new HashMap<>();

    public Router() {
        routeMap.put("/form", new DefaultOkHandler());
        routeMap.put("/put-target", new DefaultOkHandler());
        routeMap.put("/method_options", new OptionsHandler());
        routeMap.put("/method_options2", new OptionsHandler());
        routeMap.put("/redirect", new RedirectHandler());
        routeMap.put("/coffee", new FourEighteenHandler());
        routeMap.put("/tea", new DefaultOkHandler());
        routeMap.put("/parameters", new RequestParameterHandler());
        routeMap.put("/cookie", new GetCookieHandler());
        routeMap.put("/eat_cookie", new EatCookieHandler());
        routeMap.put("/logs", new AuthHandler());
        routeMap.put("/", new DirectoryHandler());
    }

    public Response getResponse(Request request) {
        return routeMap.getOrDefault(request.getPathName(), new FileContentHandler()).handle(request);
    }
}
