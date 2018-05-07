import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {
    public static void main(String args[]) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Listening for connection on port 5000 ....");
        Socket socket = null;

        while (true) {
            try {
                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));

            }
            finally{
                socket.close();
            }
        }
    }
}
