package Handlers;

import RequestHandler.Request;
import RequestHandler.RequestMethod;

import java.util.Date;

public class DefaultOkHandler implements HttpRequestHandler {


    @Override
    public String handle(Request request) {

       //if (request.getRequestMethod().equals(RequestMethod.GET)) {
            return "HTTP/1.1 200 OK\r\n\r\n" + "Today's Date is: " + new Date();
        //}
        //return "";
    }
}
