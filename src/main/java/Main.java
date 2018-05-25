import HttpServer.SimpleHttpServer;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        SimpleHttpServer server = new SimpleHttpServer(5000);
        server.start();
    }

}
