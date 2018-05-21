package Handlers;

import HttpRequest.Constants;
import HttpRequest.Request;
import HttpResponse.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageHandler implements HttpRequestHandler {

    @Override
    public Response handle(Request request) {
        String filePath = Constants.DIRECTORY_PATH + request.getPathName();

        Response response = new Response(200);
        response.setResponseHeader("Content-type:", "image/" + request.getPathName().split("\\.")[1]);
        try {
            response.setResponseBody(writeImageToResponseBody(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private byte[] writeImageToResponseBody(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
