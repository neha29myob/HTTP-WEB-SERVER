package Handlers;

import HttpRequest.Constants;
import HttpRequest.Request;
import HttpRequest.RequestMethod;
import HttpResponse.Response;

import java.io.File;

public class DirectoryHandler implements HttpRequestHandler {

    @Override
    public Response handle(Request request) {

        if (request.getRequestMethod() == RequestMethod.HEAD) {
            return (request.getPathName().equals("/")) ? new Response(200) : new Response(404);
        }

        if (request.getRequestMethod() == RequestMethod.GET) {
            Response response = new Response(200);
            File directoryPath = new File(Constants.DIRECTORY_PATH);

            String body = getFilesInDirectory(directoryPath, "");

            response.setResponseBody("<html> <body>" + body + "</body> <html>");
            return response;

        }
        return new Response(405);
    }

    public String getFilesInDirectory(File directoryPath, String body) {
        for (File files : directoryPath.listFiles()) {
            if (files.isFile()) {
                body += "<a href=/" + files.getName() + ">" + files.getName() + "</a></br>";
            } else if (files.isDirectory()) {
                getFilesInDirectory(files.getAbsoluteFile(), body);
                body += "<a href=/" + files.getName() + ">" + files.getName() + "</a></br>";
            }
        }
        return body;
    }
}


