package HttpRequest;

import java.util.HashMap;

public class Request {
    private RequestMethod requestMethod;
    private HashMap<String, String> searchQuery;
    private String pathName;
    private HashMap<String, String> requestHeader;
    private String requestBody;

    public Request(RequestMethod requestMethod, String pathName, HashMap<String, String> requestHeader, String requestBody, HashMap<String, String> searchQuery) {
        this.requestMethod = requestMethod;
        this.searchQuery = searchQuery;
        this.pathName = pathName;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
    }

    public HashMap<String, String> getRequestHeaders() {
        return requestHeader;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getPathName() {
        return pathName;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public HashMap<String, String> getSearchQuery() {
        return searchQuery;
    }

}
