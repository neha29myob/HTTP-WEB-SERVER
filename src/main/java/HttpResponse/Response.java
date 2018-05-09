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

    private String parseResponseHeader(){

        String responseHeaderString = "";
        for(String headerKey: responseHeader.keySet()){
            responseHeaderString = headerKey+":" + responseHeader.get(headerKey) + CRLF;
        }
        System.out.println(responseHeaderString);
            return responseHeaderString;
    }

//    @Override
//    public String toString() {
//        return "Response{" +
//                "statusCode=" + statusCode +
//                ", responseHeader=" + responseHeader +
//                ", responseBody='" + responseBody + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return  "HTTP/1.1 " + statusCode + " " + HttpStatusCode.httpStatusCodeMap.get(statusCode) + CRLF +
                parseResponseHeader() + CRLF;
    }
}
