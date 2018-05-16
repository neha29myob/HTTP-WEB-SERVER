package HttpResponse;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseWriter {

    public static void writeResponse(Response httpResponse, OutputStream outputStream) throws IOException {
        outputStream.write(httpResponse.getResponseStatusLine().getBytes());
        outputStream.write(httpResponse.getResponseHeader().getBytes());
        outputStream.write(httpResponse.getResponseBody());
        outputStream.flush();
        outputStream.close();
    }
}
