package HttpRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private RequestMethod requestMethod;
    private String path;
    private HashMap<String,String> searchRequest;
    private String pathName;
    private HashMap<String,String> requestHeader;
    private String requestBody;

    public Request(RequestMethod requestMethod, String pathName) {
        this.requestMethod = requestMethod;
        this.pathName = pathName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathName() {
        return pathName;
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

    public HashMap<String, String> getSearchRequest() {
        return searchRequest;
    }

    public void setSearchRequest(HashMap<String, String> searchRequest) {
        this.searchRequest = searchRequest;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestMethod=" + requestMethod +
                ", path='" + path + '\'' +
                ", searchRequest=" + searchRequest +
                ", pathName='" + pathName + '\'' +
                ", requestHeader=" + requestHeader +
                ", requestBody='" + requestBody + '\'' +
                '}';
    }
//    @Override
//    public String toString() {
//        return "Request{" +
//                "requestMethod=" + requestMethod +
//                ", pathName='" + pathName + '\'' +
//                '}';
//    }
}
