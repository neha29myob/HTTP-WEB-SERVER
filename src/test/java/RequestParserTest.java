import HttpRequest.Request;
import HttpRequest.RequestMethod;
import HttpRequest.RequestParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestParserTest {
    private RequestParser requestParser;

    @Before
    public void setup() {
        requestParser = new RequestParser();
    }

    @Test
    public void givenSimpleRequestStringReturnsRequestMethodAndPath() {
        String incomingRequest = "GET / HTTP/1.1\r\nHost: localhost:5000\r\n\r\n";
        Request expectedRequest = requestParser.parseRequest(incomingRequest);
        Assert.assertEquals(expectedRequest.getRequestMethod(), RequestMethod.GET);
        Assert.assertEquals(expectedRequest.getPathName(), "/");
    }

    @Test
    public void givenRequestWithDataReturnsRequestBody() {

        String incomingRequest = "POST /form HTTP/1.1" +
                "\r\nContent-Length: 11\r\nHost: localhost:5000" +
                "\r\n\r\nMy=Data";
        Request expectedRequest = requestParser.parseRequest(incomingRequest);
        Assert.assertEquals(expectedRequest.getRequestMethod(), RequestMethod.POST);
        Assert.assertEquals(expectedRequest.getPathName(), "/form");
        Assert.assertEquals(expectedRequest.getRequestBody(), "My=Data");
    }

    @Test
    public void givenRequestWithSpecificHeadersReturnsRequestHeaders() {

        String incomingRequest = "GET /logs HTTP/1.1" +
                "\r\nCookie: Chocolate\r\nHost: localhost:5000\r\n\r\n";
        Request expectedRequest = requestParser.parseRequest(incomingRequest);
        Assert.assertTrue(expectedRequest.getRequestHeaders().containsKey("Cookie"));
        Assert.assertTrue(expectedRequest.getRequestHeaders().containsValue("Chocolate"));
        Assert.assertEquals(expectedRequest.getRequestHeaders().get("Cookie"), "Chocolate");
    }

    @Test
    public void givenRequestWithSearchQueryReturnsRequestSearchQueryParameters() {

        String incomingRequest = "GET /parameters?variable_1=test&variable_2=stuff HTTP/1.1" +
                "\r\nHost: localhost:5000\r\n\r\n";
        Request expectedRequest = requestParser.parseRequest(incomingRequest);
        Assert.assertEquals(expectedRequest.getSearchQuery().size(), 2);
        Assert.assertTrue(expectedRequest.getSearchQuery().containsKey("variable_1"));
        Assert.assertEquals(expectedRequest.getSearchQuery().get("variable_2"), "stuff");
    }

}
