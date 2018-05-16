package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;
import HttpResponse.ResponseHandler;

import java.util.Base64;
import java.util.stream.Collectors;

public class AuthHandler implements HttpRequestHandler {

    @Override
    public Response handle(Request request) {

        String authorizationValue = request.getRequestHeader().getOrDefault("Authorization", notAuthorizedResponse().toString());
        return isUserAuthorized(authorizationValue.split(" ")[1]) ? authorizedResponse() : notAuthorizedResponse();
    }

    private boolean isUserAuthorized(String credentials) {
        String user = new String(Base64.getDecoder().decode(credentials));
        return user.split(":")[0].equals("admin") && user.split(":")[1].equals("hunter2");
    }

    private Response authorizedResponse() {
        Response response = new Response(200);
        response.setResponseBody(getLogs());
        return response;
    }

    private Response notAuthorizedResponse() {
        Response response = new Response(401);
        response.setResponseHeader("WWW-Authenticate", "Basic");
        response.setResponseBody("Access Denied");
        return response;
    }

    private String getLogs() {
        return ResponseHandler.logs.stream().collect(Collectors.joining("\r\n"));
    }


}
