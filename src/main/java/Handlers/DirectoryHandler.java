package Handlers;

import HttpRequest.Constants;
import HttpRequest.Request;
import HttpResponse.Response;

import java.io.File;

public class DirectoryHandler implements HttpRequestHandler {

    @Override
    public String handle(Request request) {

        File directoryPath = new File(Constants.DIRECTORY_PATH);

        Response response = new Response(200);
        String body = "";

        for (File files : directoryPath.listFiles()) {
            body += "<a href=/" + files.getName() + ">" + files.getName() + "</a></br>";
        }
        response.setResponseBody("<html> <body>" + body + "</body> <html>");
        return response.toString();


//        if (Arrays.asList(directoryPath.list()).contains(request.getPath())) {
//            Response response = new Response(200);
//            String body = "";
//
//            for (File files : directoryPath.listFiles()) {
//                body += "<a href=/" + files.getName() + ">" + files.getName() + "</a></br>";
//            }
//
//            response.setResponseBody("<html> <body>" + body + "</body> <html>");
//            return response.toString();
//
//        }
//        return new Response(404).toString();
    }
}

