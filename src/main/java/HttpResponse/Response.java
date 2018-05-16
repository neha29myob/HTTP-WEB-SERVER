package HttpResponse;

import java.util.HashMap;

public class Response {

    private static String CRLF = "\r\n";
    private int statusCode;
    private HashMap<String, String> responseHeader = new HashMap<>();
    private byte[] responseBody;

//    public void setResponseImage(byte[] responseImage) {
//        this.responseImage = responseImage;
//    }

    public Response(int statusCode) {
        this.statusCode = statusCode;
        this.responseBody = "".getBytes();

    }

    public void setResponseHeader(String key, String value) {
        this.responseHeader.put(key, value);
    }

    public String getResponseStatusLine() {
        return ("HTTP/1.1 " + statusCode + " " + HttpStatusCode.httpStatusCodeMap.get(statusCode)) + CRLF;
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

        String responseHeaderString = "";
//        responseHeader.forEach((k,v) -> {
//            responseHeaderString =  k + ":" + v;
//        });

        for (String headerKey : responseHeader.keySet()) {
            responseHeaderString = headerKey + ":" + responseHeader.get(headerKey) + CRLF;
        }

        return responseHeaderString + CRLF;
    }

    @Override
    public String toString() {
        return getResponseStatusLine() + CRLF +
                getResponseHeader() + CRLF + getResponseBody();
    }
}
