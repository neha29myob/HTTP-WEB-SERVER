package RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpRequestReader {
    private BufferedReader reader;

    public HttpRequestReader(InputStreamReader incomingRequest) {
        reader = new BufferedReader(incomingRequest);
    }

    public String readRequest() throws IOException {
        int line;
        StringBuilder input = new StringBuilder();
        do {
            if ((line = reader.read()) != -1) {
                input.append(Character.toChars(line));
            }
        }
        while (reader.ready());
        return input.toString();
    }

}
