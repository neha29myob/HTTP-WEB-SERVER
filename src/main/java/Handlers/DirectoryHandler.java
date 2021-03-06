package Handlers;

import HttpRequest.Constants;
import HttpRequest.Request;
import HttpRequest.RequestMethod;
import HttpResponse.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DirectoryHandler implements HttpRequestHandler {

    @Override
    public Response handle(Request request) {

        if (request.getRequestMethod() == RequestMethod.HEAD) {
            return (request.getPathName().equals("/")) ? new Response(200) : new Response(404);
        }
        if (request.getRequestMethod() == RequestMethod.GET) {
            return listFileResponse();
        }
        return new Response(405);
    }

    private Response listFileResponse() {

        Response response = new Response(200);
        File directoryPath = new File(Constants.DIRECTORY_PATH);
        String body = getFilesInDirectory(directoryPath);

//        File[] files = directoryPath.listFiles();
//        for(File file: files){
//            if(file.isDirectory()){
//                directoryPath = new File(directoryPath + "/cat-form");
//            }
//        }

        response.setResponseBody("<html> <body>" + body + "</body> <html>");
        return response;

    }

    private String getFilesInDirectory(File directoryPath) {

        String body = Arrays.stream(directoryPath.listFiles())
                .map(file -> "<a href=/" + file.getName() + ">" + file.getName() + "</a></br>")
                .collect(Collectors.joining(""));
        return body;
    }
}


