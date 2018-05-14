package HttpResponse;

import java.util.HashMap;

public class Response {

    private int statusCode;
    private HashMap<String,String> responseHeader = new HashMap<>();
    private String responseBody;
    private static String CRLF = "\r\n";

    public Response(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseHeader(String key, String value){
        this.responseHeader.put(key, value);
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    private  String getResponseStatusLine(){
        return "HTTP/1.1 " + statusCode + " " + HttpStatusCode.httpStatusCodeMap.get(statusCode);
    }

    private String getResponseBody() {
        return responseBody;
    }

    private String getResponseHeader(){

        String responseHeaderString = "";
//        responseHeader.forEach((k,v) -> {
//            responseHeaderString =  k + ":" + v;
//        });

        for(String headerKey: responseHeader.keySet()){
            responseHeaderString = headerKey + ":" + responseHeader.get(headerKey) + CRLF;
        }
        System.out.println(responseHeaderString);
            return responseHeaderString;
    }

    @Override
    public String toString() {
        return  getResponseStatusLine() + CRLF +
                getResponseHeader() + CRLF + getResponseBody() ;
    }
}
