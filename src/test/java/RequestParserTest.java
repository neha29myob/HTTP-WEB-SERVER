import HttpRequest.Request;
import HttpRequest.RequestMethod;
import HttpRequest.RequestParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestParserTest {
    private RequestParser requestParser;

    @Before
    public void setup(){
       requestParser = new RequestParser();
    }

    @Test
    public void givenSimpleRequestStringReturnsRequestMethodAndPath(){
        String incomingRequest = "GET / HTTP/1.1\r\nHost: localhost:5000\r\n\r\n";
        Request expectedRequest = requestParser.parseRequest(incomingRequest);
        Assert.assertEquals(expectedRequest.getRequestMethod(), RequestMethod.GET);
        Assert.assertEquals(expectedRequest.getPathName(), "/");
    }

}
