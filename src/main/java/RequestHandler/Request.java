package RequestHandler;

public class Request {
    private RequestType action;
    private String path;

    public Request(RequestType type, String path) {
        this.action = type;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public RequestType getAction() {
        return action;
    }

}
