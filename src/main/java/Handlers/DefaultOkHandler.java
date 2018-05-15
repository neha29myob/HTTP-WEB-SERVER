package Handlers;

import HttpRequest.Request;
import HttpResponse.Response;

public class DefaultOkHandler implements HttpRequestHandler {

    @Override
    public String handle(Request request) {

        return new Response(200).toString();
        //if (request.getRequestMethod().equals(RequestMethod.GET)) {
        //return "HTTP/1.1 200 OK\r\n\r\n" + "Today's Date is: " + new Date();
        //}
        //return "";
    }
}
