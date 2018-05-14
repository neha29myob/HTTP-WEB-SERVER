package Handlers;

import HttpRequest.Request;
import HttpRequest.RequestMethod;
import HttpResponse.Response;

public class CrudHandler implements HttpRequestHandler {
    private String data;

    @Override
    public String handle(Request request) {

        if (request.getRequestMethod() == RequestMethod.GET) {
            return handleGetRequest(request);
        }
        if (request.getRequestMethod() == RequestMethod.POST) {
            return handlePostRequest(request);
        }
        return new Response(405).toString();
    }

    private String handlePostRequest(Request request) {
        Response response = new Response(201);
        String filePath = request.getRequestBody().split("=")[0];
        this.data = request.getRequestBody();
        System.out.println("Request body is " + data);

        String catFormFile = request.getPath() + "/" + filePath;
        response.setResponseHeader("Location", catFormFile);
        response.setResponseBody(request.getRequestBody());
//            File file = new File(Constants.DIRECTORY_PATH + catFormFile);
//            try {
//                BufferedWriter output = new BufferedWriter(new FileWriter(file));
//                output.write(request.getRequestBody());
//                output.flush();
//                output.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        return response.toString();
    }

    private String handleGetRequest(Request request) {

        if (this.data == null) {

        //if (request.getRequestBody().equals("")) {
            return new Response(404).toString();
        }
            Response response = new Response(200);
            response.setResponseBody(request.getRequestBody());
            return response.toString();
    }
}
