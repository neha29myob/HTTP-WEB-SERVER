package RequestHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HTTPRequestParser {

    public Request parseRequest(String requestString) {

        String[] httpRequest = requestString.split("\r\n\r\n", 2);

        String httpRequestHeader = httpRequest[0];
        System.out.println("http request " + httpRequest);

        String requestLine = httpRequestHeader.split("\r\n", 2)[0];
        String headerLine = httpRequestHeader.split("\r\n", 2)[1];

        Request request = createRequestLine(requestLine);

        List<String> headerList = Arrays.asList(headerLine.split("\r\n"));
        request.setRequestHeader(createRequestHeader(headerList));
        request.setRequestBody(httpRequest[1]);

        return request;

    }

    private HashMap<String, String> createRequestHeader(List<String> headerList) {
        HashMap<String, String> requestHeaderMap = new HashMap<>();

        headerList.forEach(headerItem -> {
            String[] keyValue = headerItem.split(":");
            requestHeaderMap.putIfAbsent(keyValue[0].trim(), keyValue[1].trim());
        });
        return requestHeaderMap;
    }

    private Request createRequestLine(String requestLine) {

        RequestMethod requestMethod = RequestMethod.valueOf(requestLine.split(" ")[0]);
        String path = requestLine.split(" ")[1];

        return new Request(requestMethod, path);
    }

}
