import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHttpServer {

    ExecutorService serverExecutorPool = Executors.newFixedThreadPool(10);
    private int port;

    public SimpleHttpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                serverExecutorPool.submit(new ResponseHandler(serverSocket.accept()));
            }
        } finally {
            serverExecutorPool.shutdown();
        }
    }
}

