package HttpResponse;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Response {

    private static String CRLF = "\r\n";
    private String HTTP_PROTOCOL = "HTTP/1.1 ";

    private int statusCode;
    private HashMap<String, String> responseHeader = new HashMap<>();
    private byte[] responseBody;

    public Response(int statusCode) {
        this.statusCode = statusCode;
        this.responseBody = "".getBytes();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setResponseHeader(String key, String value) {
        this.responseHeader.put(key, value);
    }

    public String getResponseStatusLine() {
        return (HTTP_PROTOCOL + statusCode + " " + HttpStatusCode.httpStatusCodeMap.get(statusCode)) + CRLF;
    }

    public byte[] getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody.getBytes();
    }

    public void setResponseBody(byte[] responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseHeader() {
        String responseHeaderString = responseHeader.entrySet()
                .stream()
                .map(map -> map.getKey() + ":" + map.getValue() + CRLF)
                .collect(Collectors.joining(""));
        return responseHeaderString + CRLF;
    }

    @Override
    public String toString() {
        return getResponseStatusLine() + CRLF +
                getResponseHeader() + CRLF + getResponseBody();
    }
}
