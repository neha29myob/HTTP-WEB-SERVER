package HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RequestParser {

    private static HashMap<String, String> parseRequestHeaders(String headers) {
        HashMap<String, String> requestHeaderMap = new HashMap<>();
        List<String> headerList = Arrays.asList(headers.split("\r\n"));

        headerList.forEach(headerItem -> {
            String[] keyValue = headerItem.split(":");
            requestHeaderMap.putIfAbsent(keyValue[0].trim(), keyValue[1].trim());
        });
        return requestHeaderMap;
    }

    public Request parseRequest(String requestString) {

        String[] httpRequest = requestString.split("\r\n\r\n", 2);
        String[] requestHeader = httpRequest[0].split("\r\n", 2);
        String requestLine = requestHeader[0];

        return new Request(parseRequestMethod(requestLine)
                , parsePathName(requestLine)
                , parseRequestHeaders(requestHeader[1])
                , httpRequest[1]
                , getQueryPairs(requestLine));
    }

    private RequestMethod parseRequestMethod(String requestLine) {
        try {
            return RequestMethod.valueOf(requestLine.split(" ")[0]);
        } catch (IllegalArgumentException e) {
            return RequestMethod.BAD;
        }
    }

    private String parsePathName(String requestLine) {
        String path = requestLine.split(" ")[1];
        String pathName = path.split("\\?")[0];
        return pathName;
    }

    private HashMap<String, String> getQueryPairs(String requestLine) {

        HashMap<String, String> queryPairs = new HashMap<>();

        if (isRequestWithSearchQuery(requestLine)) {
            queryPairs = getQueryParameters(requestLine.split(" ")[1]);
        }
        return queryPairs;
    }

    private boolean isRequestWithSearchQuery(String requestLine) {
        return !(getQuery(requestLine.split(" ")[1]).isEmpty());
    }

    private String getQuery(String path) {
        if (path.split("\\?").length == 2) {
            return path.split("\\?")[1];
        }
        return "";
    }

    private HashMap<String, String> getQueryParameters(String path) {
        HashMap<String, String> parameterPair = new HashMap<>();
        List<String> parameters = Arrays.asList(getQuery(path).split("&"));
        parameters.forEach(parameter -> {
            int keyIndex = parameter.indexOf("=");
            createQueryParameterMap(parameterPair, parameter, keyIndex);
        });

        return parameterPair;
    }

    private void createQueryParameterMap(HashMap<String, String> parameterPair, String parameter, int keyIndex) {
        try {
            parameterPair.put(parameter.substring(0, keyIndex), URLDecoder.decode(parameter.substring(keyIndex + 1), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
