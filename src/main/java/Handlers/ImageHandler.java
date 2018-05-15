package Handlers;

import HttpRequest.Constants;
import HttpRequest.Request;
import HttpResponse.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageHandler implements HttpRequestHandler {

    @Override
    public String handle(Request request) {
        String filePath = Constants.DIRECTORY_PATH + request.getPath();

        Response response = new Response(200);
        System.out.println(request.getPath().split("\\.")[1]);
        response.setResponseHeader("Content-type:", "image/" + request.getPath().split("\\.")[1]);
//        try {
//            response.setResponseImage(writeImageToResponseBody(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return response.toString();
    }

    private byte[] writeImageToResponseBody(String path) throws IOException {

        return Files.readAllBytes(Paths.get(path));
    }
}
