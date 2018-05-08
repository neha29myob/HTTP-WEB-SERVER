package RequestHandler;

import java.io.IOException;
import java.io.InputStreamReader;

public class HTTPRequestParser {
    private HttpRequestReader requestReader;

    public HTTPRequestParser(InputStreamReader incomingRequest) {
        requestReader = new HttpRequestReader(incomingRequest);
    }

    public Request parseRequest() throws IOException {

        String input = requestReader.readRequest();
        System.out.println(input);
        String requestLine = input.split("\n")[0];
        RequestType requestType = RequestType.valueOf(requestLine.split(" ")[0]);
        String path = requestLine.split(" ")[1];

        return new Request(requestType, path);
    }

}
