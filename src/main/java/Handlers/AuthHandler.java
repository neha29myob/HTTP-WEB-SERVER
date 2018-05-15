package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;
import HttpResponse.ResponseHandler;

import java.util.Base64;

public class AuthHandler implements HttpRequestHandler {
//    private List<String> logs;
//
//    public AuthHandler(List<String> logs) {
//        this.logs = logs;
//    }

    @Override
    public String handle(Request request) {

        System.out.println(request.getRequestHeader().keySet());

       if(request.getRequestHeader().containsKey("Authorization")){
           String credentials = request.getRequestHeader().get("Authorization");
           String usernamePassword = credentials.split(" ")[1];
           if(isUserAuthorized(usernamePassword)) {
               Response response = new Response(200);
               response.setResponseBody(getLogs());
               return response.toString();
           }
       }

        Response response = new Response(401);
        response.setResponseBody("Access Denied");
        response.setResponseHeader("Authorization", "Basic");
        response.setResponseHeader("WWW-Authenticate", "Basic");
        return response.toString();
    }

    private String getLogs() {
        String body = "";
        for(String log: ResponseHandler.logs){
            body+= (log + "\r\n");
        }
        return body;
    }

    private boolean isUserAuthorized(String credentials) {
        String user = new String(Base64.getDecoder().decode(credentials));
        //String user = String.valueOf(Base64.getDecoder().decode(credentials));
        return user.split(":")[0].equals("admin") && user.split(":")[1].equals("hunter2");

    }

}
