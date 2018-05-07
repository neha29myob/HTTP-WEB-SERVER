import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {
    public static void main(String args[]) throws IOException {
    try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                try (Socket connection = serverSocket.accept()) {
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    while(inFromServer.ready()){
                        inFromServer.read();
                    }
                    Date today = new Date();
                    String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                    connection.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                }
            }
        }
    }
}
