package Handlers;

import HttpRequest.Constants;
import HttpRequest.Request;
import HttpRequest.RequestMethod;
import HttpResponse.Response;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.joining;

public class FileContentHandler implements HttpRequestHandler {

    @Override
    public String handle(Request request) {

        String filePath = Constants.DIRECTORY_PATH + request.getPath();

        if (request.getRequestMethod() == RequestMethod.GET) {

            if(isImage(request)){
                return new ImageHandler().handle(request);
            }
            try {
                Response response = new Response(200);
                response.setResponseBody(readFile(filePath));
                response.setResponseHeader("content-type", "text/plain");
                return response.toString();
            } catch (IOException e) {
                return new Response(404).toString();
            }
        }

        if (request.getRequestMethod() == RequestMethod.HEAD) {
            return (request.getPath().equals("/")) ? new Response(200).toString() : new Response(404).toString();
        }

        if (request.getRequestMethod() == RequestMethod.POST && !(request.getRequestBody().equals(""))) {

            Response response = new Response(201);
            response.setResponseBody(request.getRequestBody());

            String catFormFile = request.getPath() + "/" + request.getRequestBody().split("=")[0];
            response.setResponseHeader("Location", catFormFile);
            File file = new File(Constants.DIRECTORY_PATH + catFormFile);
            try {
                writeToFile(file.toString(), request.getRequestBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString();
        }

        if (request.getRequestMethod() == RequestMethod.PUT && !(request.getRequestBody().equals(""))) {
            Response response = new Response(200);
            response.setResponseBody(request.getRequestBody());
            try {
                writeToFile(filePath, request.getRequestBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString();
        }

        if (request.getRequestMethod() == RequestMethod.PATCH) {
            Response response = new Response(204);
            response.setResponseBody(request.getRequestBody());
            try {
                writeToFile(filePath, request.getRequestBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString();
        }

        if (request.getRequestMethod() == RequestMethod.DELETE) {
            Response response = new Response(200);
            File toDeleteFile = new File(filePath);
            if (toDeleteFile.exists() && !toDeleteFile.isDirectory())
                toDeleteFile.delete();
            return response.toString();
        }

        return new Response(405).toString();
    }

    private boolean isImage(Request request) {
        return request.getPath().contains(".png") || request.getPath().contains(".jpeg") || request.getPath().contains(".gif");
    }


    private void writeToFile(String catFormFile, String requestBody) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(catFormFile));
        fileWriter.write(requestBody);
        fileWriter.flush();
        fileWriter.close();
    }

    private String readFile(String fileName) throws IOException {
        try (BufferedReader r = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
            String fileContents = r.lines().collect(joining());
            return fileContents;
        }
    }
}
