package HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RequestParser {

    public static Request parseRequest(String requestString) {

        String[] httpRequest = requestString.split("\r\n\r\n", 2);

        String httpRequestHeader = httpRequest[0];

        String requestLine = httpRequestHeader.split("\r\n", 2)[0];
        String requestHeader = httpRequestHeader.split("\r\n", 2)[1];

        Request request = createRequestLine(requestLine);
        //request.setPath(requestLine.split(" ")[1]);

        if (!(getQuery(requestLine.split(" ")[1]).isEmpty())) {
            HashMap<String, String> queryPairs = getQueryParameters(requestLine.split(" ")[1]);
            request.setSearchQuery(queryPairs);
        }

        List<String> headerList = Arrays.asList(requestHeader.split("\r\n"));

        request.setRequestHeader(createRequestHeader(headerList));
        request.setRequestBody(httpRequest[1]);

        return request;

    }

    private static HashMap<String, String> createRequestHeader(List<String> headerList) {
        HashMap<String, String> requestHeaderMap = new HashMap<>();

        headerList.forEach(headerItem -> {
            String[] keyValue = headerItem.split(":");
            requestHeaderMap.putIfAbsent(keyValue[0].trim(), keyValue[1].trim());
        });
        return requestHeaderMap;
    }


    private static String getQuery(String path) {
        if (path.split("\\?").length == 2) {
            System.out.println("searchQuery" + path.split("\\?")[1]);
            return path.split("\\?")[1];
        }
        return "";

    }

    private static Request createRequestLine(String requestLine) {

        RequestMethod requestMethod = getRequestMethod(requestLine);
        String path = requestLine.split(" ")[1];
        String pathName = path.split("\\?")[0];
        return new Request(requestMethod, pathName);
    }

    private static RequestMethod getRequestMethod(String requestLine) {
        try {
            return RequestMethod.valueOf(requestLine.split(" ")[0]);
        } catch (IllegalArgumentException e) {
            return RequestMethod.BAD;
        }
    }

    public static HashMap<String, String> getQueryParameters(String path) {
        HashMap<String, String> parameterPair = new HashMap<>();
        String[] parameters = getQuery(path).split("&");
        for (String parameter : parameters) {
            int idx = parameter.indexOf("=");
            try {
                parameterPair.put(parameter.substring(0, idx), URLDecoder.decode(parameter.substring(idx + 1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return parameterPair;
    }

}
