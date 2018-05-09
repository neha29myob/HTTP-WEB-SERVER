package HttpResponse;

import java.util.HashMap;

public class HttpStatusCode {
        public static final HashMap<Integer, String> httpStatusCodeMap = new HashMap<Integer, String>();

        static {
            httpStatusCodeMap.put(200, "OK");
            httpStatusCodeMap.put(404, "Not Found");
            httpStatusCodeMap.put(418, "I'm a teapot");
        }
    }
//    put(200, "OK");
//    put(418, "I'm a teapot");
//    put(401, "Unauthorized");
//    put(404, "Not Found");
//    put(405, "Method Not Allowed");
//    put(302, "Found");
//    put(204, "No Content");
//    put(206, "Partial Content");
