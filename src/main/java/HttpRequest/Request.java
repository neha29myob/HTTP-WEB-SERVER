package HttpRequest;

import java.util.HashMap;

public class Request {
    private RequestMethod requestMethod;
    private HashMap<String, String> searchQuery;
    private String pathName;
    private HashMap<String, String> requestHeader;
    private String requestBody;

    public Request(RequestMethod requestMethod, String pathName) {
        this.requestMethod = requestMethod;
        this.pathName = pathName;
    }

    public HashMap<String, String> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(HashMap<String, String> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
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

    public void setSearchQuery(HashMap<String, String> searchRequest) {
        this.searchQuery = searchRequest;
    }


    @Override
    public String toString() {
        return "Request{" +
                "requestMethod=" + requestMethod +
                ", searchRequest=" + searchQuery +
                ", pathName='" + pathName + '\'' +
                ", requestHeader=" + requestHeader +
                ", requestBody='" + requestBody + '\'' +
                '}';
    }

}
