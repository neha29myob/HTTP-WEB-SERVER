package HttpRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestReader {

    public static String readRequest(InputStreamReader incomingRequest) throws IOException {

        BufferedReader reader = new BufferedReader(incomingRequest);
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
