package RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HTTPRequestParser {
    private InputStreamReader incomingRequest;

    public HTTPRequestParser(InputStreamReader incomingRequest) {
        this.incomingRequest = incomingRequest;
    }

    public Request processRequest() throws IOException {
        String input = readInput();
        String requestLine =input.split("\n")[0];
        RequestType requestType = RequestType.valueOf(requestLine.split(" ")[0]);
        String path = requestLine.split(" ")[1];
        return new Request(requestType,path);
    }

    public String readInput () throws IOException {
        int line;
        StringBuilder input = new StringBuilder();
        BufferedReader reader = new BufferedReader(incomingRequest);
        do {
            if ((line = reader.read()) != -1) {
                input.append(Character.toChars(line));
            }
        }
        while (reader.ready());
        return input.toString();
    }
}
