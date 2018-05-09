package HttpRequest;

import java.util.HashMap;

public class Request {
    private RequestMethod requestMethod;
    private String path;
    private HashMap<String,String> requestHeader;
    private String requestBody;

    public Request(RequestMethod requestMethod, String path) {
        this.requestMethod = requestMethod;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestHeader(HashMap<String, String> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestMethod=" + requestMethod +
                ", path='" + path + '\'' +
                '}';
    }
}
